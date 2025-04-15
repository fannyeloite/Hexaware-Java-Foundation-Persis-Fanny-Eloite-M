package com.hexaware.main;

import com.hexaware.dao.impl.*;
import com.hexaware.entity.*;

import java.time.LocalDate;
import java.util.List;

public class DAOTest {

	public static void main(String[] args) {
		System.out.println("===== DAO TEST STARTED =====");

        // 1. Insert Customer
        Customer customer = new Customer(1001, "Fanny", "Grace", "fanny.test@gmail.com", "9976543771", "Chennai");
        new CustomerDAOImpl().insertCustomer(customer);

        // 2. Insert Product
        Product product = new Product(2001, "Wireless Mouse", "Ergonomic design", 999.99);
        new ProductDAOImpl().insertProduct(product);

        // 3. Insert Inventory
        Inventory inventory = new Inventory(3001, product, 25, LocalDate.now());
        new InventoryDAOImpl().insertInventory(inventory);

        // 4. Insert Order
        Order order = new Order(4001, customer, LocalDate.now(), 0.0, "Placed");
        new OrderDAOImpl().insertOrder(order);

        // 5. Insert OrderDetail
        OrderDetail detail = new OrderDetail(5001, order, product, 2, 49.99);
        new OrderDetailDAOImpl().insertOrderDetail(detail);

        // 6. Fetch All Customers
        System.out.println("\nAll Customers:");
        List<Customer> customers = new CustomerDAOImpl().getAllCustomers();
        for (Customer c : customers) {
            c.getCustomerDetails();
            System.out.println();
        }

        // 7. Fetch All Products
        System.out.println("\nAll Products:");
        List<Product> products = new ProductDAOImpl().getAllProducts();
        for (Product p : products) {
            p.GetProductDetails();
            System.out.println();
        }

        // 8. Fetch Inventory
        System.out.println("\nAll Inventory:");
        List<Inventory> invList = new InventoryDAOImpl().getAllInventory();
        for (Inventory i : invList) {
            i.ListAllProducts();
            System.out.println();
        }

        // 9. Fetch Order
        System.out.println("\nOrder Summary:");
        Order fetchedOrder = new OrderDAOImpl().getOrderById(4001);
        if (fetchedOrder != null) {
            fetchedOrder.GetOrderDetails();
        }

        // 10. Fetch Order Details
        System.out.println("\nOrder Details:");
        List<OrderDetail> orderDetails = new OrderDetailDAOImpl().getOrderDetailsByOrderId(4001);
        for (OrderDetail d : orderDetails) {
            d.getOrderDetailInfo();
            System.out.println();
        }

        System.out.println("===== DAO TEST COMPLETE =====");
    }

	}


