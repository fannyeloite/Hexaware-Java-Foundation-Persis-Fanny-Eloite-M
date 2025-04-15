package com.hexaware.entity;

import java.time.LocalDate;

public class Inventory {
	private int inventoryID;
    private Product product; // Composition
    private int quantityInStock;
    private LocalDate lastStockUpdate;

    // Constructor
    public Inventory(int inventoryID, Product product, int quantityInStock, LocalDate lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    // Getters and Setters
    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        }
    }

    public LocalDate getLastStockUpdate() {
        return lastStockUpdate;
    }

    public void setLastStockUpdate(LocalDate lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    // Inventory methods
    public void AddToInventory(int quantity) {
        if (quantity > 0) {
            this.quantityInStock += quantity;
            this.lastStockUpdate = LocalDate.now();
        }
    }

    public void RemoveFromInventory(int quantity) {
        if (quantity > 0 && quantity <= this.quantityInStock) {
            this.quantityInStock -= quantity;
            this.lastStockUpdate = LocalDate.now();
        }
    }

    public void UpdateStockQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
            this.lastStockUpdate = LocalDate.now();
        }
    }

    public boolean IsProductAvailable(int quantityToCheck) {
        return quantityToCheck <= quantityInStock;
    }

    public double GetInventoryValue() {
        return product.getPrice() * quantityInStock;
    }

    public void ListLowStockProducts(int threshold) {
        if (quantityInStock < threshold) {
            System.out.println("Low Stock Alert: " + product.getProductName() + " (Qty: " + quantityInStock + ")");
        }
    }

    public void ListOutOfStockProducts() {
        if (quantityInStock == 0) {
            System.out.println("Out of Stock: " + product.getProductName());
        }
    }

    public void ListAllProducts() {
        product.GetProductDetails();
        System.out.println("Stock: " + quantityInStock);
        System.out.println("Last Updated: " + lastStockUpdate);
    }
}