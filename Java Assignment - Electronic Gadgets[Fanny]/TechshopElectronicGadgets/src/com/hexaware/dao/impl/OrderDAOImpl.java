package com.hexaware.dao.impl;

import com.hexaware.dao.OrderDAO;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Order;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
	 private final String dbConfigPath = "resources/db.properties";

	    @Override
	    public void insertOrder(Order order) {
	        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
	            String query = "INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount, Status) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setInt(1, order.getOrderID());
	            ps.setInt(2, order.getCustomer().getCustomerID());
	            ps.setDate(3, Date.valueOf(order.getOrderDate()));
	            ps.setDouble(4, order.getTotalAmount());
	            ps.setString(5, order.getStatus());
	            ps.executeUpdate();
	            System.out.println("✅ Order inserted successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Override
	    public double getTotalSales() throws SQLException {
	        double totalSales = 0;
	        String query = "SELECT SUM(totalamount) AS totalSales FROM orders WHERE status = 'Shipped'";

	        try (Connection conn = DBConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
	            if (rs.next()) {
	                totalSales = rs.getDouble("totalSales");
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Error calculating total sales", e);
	        }
	        return totalSales;
	    }
	    
	  

	    @Override
	    public void updateOrderStatus(int orderID, String newStatus) {
	        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
	            String query = "UPDATE Orders SET Status=? WHERE OrderID=?";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setString(1, newStatus);
	            ps.setInt(2, orderID);
	            ps.executeUpdate();
	            System.out.println("✅ Order status updated!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void cancelOrder(int orderID) {
	        updateOrderStatus(orderID, "Cancelled");
	    }

	    @Override
	    public Order getOrderById(int orderID) {
	        Order order = null;
	        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
	            String query = "SELECT o.*, c.FirstName, c.LastName, c.Email, c.Phone, c.Address FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID WHERE o.OrderID=?";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setInt(1, orderID);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                Customer customer = new Customer(
	                        rs.getInt("CustomerID"),
	                        rs.getString("FirstName"),
	                        rs.getString("LastName"),
	                        rs.getString("Email"),
	                        rs.getString("Phone"),
	                        rs.getString("Address")
	                );
	                order = new Order(
	                        rs.getInt("OrderID"),
	                        customer,
	                        rs.getDate("OrderDate").toLocalDate(),
	                        rs.getDouble("TotalAmount"),
	                        rs.getString("Status")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return order;
	    }


	    @Override
	    public List<Order> getAllOrders() {
	        List<Order> orders = new ArrayList<>();
	        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
	            String query = "SELECT o.*, c.FirstName, c.LastName, c.Email, c.Phone, c.Address FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Customer customer = new Customer(
	                        rs.getInt("CustomerID"),
	                        rs.getString("FirstName"),
	                        rs.getString("LastName"),
	                        rs.getString("Email"),
	                        rs.getString("Phone"),
	                        rs.getString("Address")
	                );
	                Order order = new Order(
	                        rs.getInt("OrderID"),
	                        customer,
	                        rs.getDate("OrderDate").toLocalDate(),
	                        rs.getDouble("TotalAmount"),
	                        rs.getString("Status")
	                );
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orders;
	    }


	    @Override
	    public List<Order> getOrdersByCustomerId(int customerID) {
	        List<Order> orders = new ArrayList<>();
	        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
	            String query = "SELECT o.*, c.FirstName, c.LastName, c.Email, c.Phone, c.Address FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID WHERE c.CustomerID=?";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setInt(1, customerID);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Customer customer = new Customer(
	                        rs.getInt("CustomerID"),
	                        rs.getString("FirstName"),
	                        rs.getString("LastName"),
	                        rs.getString("Email"),
	                        rs.getString("Phone"),
	                        rs.getString("Address")
	                );
	                Order order = new Order(
	                        rs.getInt("OrderID"),
	                        customer,
	                        rs.getDate("OrderDate").toLocalDate(),
	                        rs.getDouble("TotalAmount"),
	                        rs.getString("Status")
	                );
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orders;
	    }
	}


