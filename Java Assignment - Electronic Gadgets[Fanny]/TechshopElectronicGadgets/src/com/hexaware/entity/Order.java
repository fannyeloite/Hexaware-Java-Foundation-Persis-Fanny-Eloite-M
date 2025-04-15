package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private Customer customer; // Composition
    private LocalDate orderDate;
    private double totalAmount;
    private String status;

    private List<OrderDetail> orderDetailsList = new ArrayList<>();

    // Constructor
    public Order(int orderID, Customer customer, LocalDate orderDate, double totalAmount, String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void addOrderDetail(OrderDetail detail) {
        orderDetailsList.add(detail);
    }

    // Methods
    public void CalculateTotalAmount() {
        totalAmount = 0;
        for (OrderDetail detail : orderDetailsList) {
            totalAmount += detail.calculateSubtotal();
        }
    }

    public void GetOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Order Details:");
        for (OrderDetail detail : orderDetailsList) {
            detail.getOrderDetailInfo();
        }
        System.out.println("Total Amount: ₹" + totalAmount);
    }

    public void UpdateOrderStatus(String newStatus) {
        setStatus(newStatus);
        System.out.println("Order status updated to: " + newStatus);
    }

    public void CancelOrder() {
        status = "Cancelled";

        // Normally we would update Inventory here, but since Inventory is not directly linked here,
        // we simply acknowledge the logic.
        System.out.println("Order cancelled. You may need to update inventory separately.");
    }
}
