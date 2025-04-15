package com.hexaware.dao.impl;

import com.hexaware.dao.ProductDAO;
import com.hexaware.entity.Product;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
	private final String dbConfigPath = "resources/db.properties";

    @Override
    public void insertProduct(Product product) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "INSERT INTO Products (ProductID, ProductName, Description, Price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, product.getProductID());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.executeUpdate();
            System.out.println("✅ Product inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "UPDATE Products SET ProductName=?, Description=?, Price=? WHERE ProductID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getProductID());
            ps.executeUpdate();
            System.out.println("✅ Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productID) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "DELETE FROM Products WHERE ProductID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();
            System.out.println("🗑️ Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int productID) {
        Product product = null;
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT * FROM Products WHERE ProductID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT * FROM Products";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public List<Product> searchProductsByName(String keyword) throws SQLException {
		
		List<Product> results = new ArrayList<>();
	    Connection conn = DBConnector.getConnection();

	    String query = "SELECT * FROM products WHERE productname LIKE ?";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, "%" + keyword + "%");

	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        Product p = new Product(
	            rs.getInt("productid"),
	            rs.getString("productname"),
	            rs.getString("description"),
	            rs.getDouble("price")
	        );
	        results.add(p);
	    }

	    conn.close();
	    return results;
	}
}

