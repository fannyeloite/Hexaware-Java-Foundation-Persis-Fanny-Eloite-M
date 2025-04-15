package com.hexaware.dao.impl;

import com.hexaware.dao.OrderDetailDAO;
import com.hexaware.entity.Order;
import com.hexaware.entity.OrderDetail;
import com.hexaware.entity.Product;
import com.hexaware.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
	private final String dbConfigPath = "resources/db.properties";

    @Override
    public void insertOrderDetail(OrderDetail detail) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity, Discount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, detail.getOrderDetailID());
            ps.setInt(2, detail.getOrder().getOrderID());
            ps.setInt(3, detail.getProduct().getProductID());
            ps.setInt(4, detail.getQuantity());
            ps.setDouble(5, detail.getDiscount());
            ps.executeUpdate();
            System.out.println("✅ Order detail inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderDetail(OrderDetail detail) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "UPDATE OrderDetails SET Quantity=?, Discount=? WHERE OrderDetailID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, detail.getQuantity());
            ps.setDouble(2, detail.getDiscount());
            ps.setInt(3, detail.getOrderDetailID());
            ps.executeUpdate();
            System.out.println("✅ Order detail updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderDetail(int orderDetailID) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "DELETE FROM OrderDetails WHERE OrderDetailID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderDetailID);
            ps.executeUpdate();
            System.out.println("🗑️ Order detail deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderID) {
        List<OrderDetail> details = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT d.*, p.ProductName, p.Description, p.Price FROM OrderDetails d JOIN Products p ON d.ProductID = p.ProductID WHERE d.OrderID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                OrderDetail detail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        new Order(orderID, null, null, 0, null), // partial object
                        product,
                        rs.getInt("Quantity"),
                        rs.getDouble("Discount")
                );
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

}
