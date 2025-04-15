package com.hexaware.dao.impl;

import com.hexaware.dao.CustomerDAO;
import com.hexaware.entity.Customer;
import com.hexaware.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
	private final String dbConfigPath = "resources/db.properties";

    @Override
    public void insertCustomer(Customer customer) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "INSERT INTO Customers (CustomerID, FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customer.getCustomerID());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddress());
            ps.executeUpdate();
            System.out.println("✅ Customer inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "UPDATE Customers SET FirstName=?, LastName=?, Email=?, Phone=?, Address=? WHERE CustomerID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getAddress());
            ps.setInt(6, customer.getCustomerID());
            ps.executeUpdate();
            System.out.println("✅ Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerID) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "DELETE FROM Customers WHERE CustomerID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerID);
            ps.executeUpdate();
            System.out.println("🗑️ Customer deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerID) {
        Customer customer = null;
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT * FROM Customers WHERE CustomerID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT * FROM Customers";
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
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
	
	


