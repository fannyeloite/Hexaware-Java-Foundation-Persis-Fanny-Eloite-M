package com.hexaware.dao.impl;

import com.hexaware.dao.InventoryDAO;
import com.hexaware.entity.Inventory;
import com.hexaware.entity.Product;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
	private final String dbConfigPath = "resources/db.properties";

    @Override
    public void insertInventory(Inventory inventory) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "INSERT INTO Inventory (InventoryID, ProductID, QuantityInStock, LastStockUpdate) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, inventory.getInventoryID());
            ps.setInt(2, inventory.getProduct().getProductID());
            ps.setInt(3, inventory.getQuantityInStock());
            ps.setDate(4, Date.valueOf(inventory.getLastStockUpdate()));
            ps.executeUpdate();
            System.out.println("✅ Inventory inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInventory(Inventory inventory) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "UPDATE Inventory SET QuantityInStock=?, LastStockUpdate=? WHERE InventoryID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, inventory.getQuantityInStock());
            ps.setDate(2, Date.valueOf(inventory.getLastStockUpdate()));
            ps.setInt(3, inventory.getInventoryID());
            ps.executeUpdate();
            System.out.println("✅ Inventory updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventory(int inventoryID) {
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "DELETE FROM Inventory WHERE InventoryID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, inventoryID);
            ps.executeUpdate();
            System.out.println("🗑️ Inventory deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventory getInventoryById(int inventoryID) {
        Inventory inventory = null;
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT i.*, p.ProductName, p.Description, p.Price FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID WHERE InventoryID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, inventoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                inventory = new Inventory(
                        rs.getInt("InventoryID"),
                        product,
                        rs.getInt("QuantityInStock"),
                        rs.getDate("LastStockUpdate").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT i.*, p.ProductName, p.Description, p.Price FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                Inventory inventory = new Inventory(
                        rs.getInt("InventoryID"),
                        product,
                        rs.getInt("QuantityInStock"),
                        rs.getDate("LastStockUpdate").toLocalDate()
                );
                list.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Inventory getInventoryByProductId(int productId) throws SQLException {
        // method body
    	Inventory inventory = null;
        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT * FROM inventory WHERE productid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new ProductDAOImpl().getProductById(productId);
                inventory = new Inventory(
                    rs.getInt("inventoryid"),
                    product,
                    rs.getInt("quantityinstock"),
                    rs.getDate("laststockupdate").toLocalDate()
                );
            }
        }
        return inventory;
    }


    @Override
    public List<Inventory> getLowStockInventory(int threshold) {
        List<Inventory> lowStock = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbConfigPath)) {
            String query = "SELECT i.*, p.ProductName, p.Description, p.Price FROM Inventory i JOIN Products p ON i.ProductID = p.ProductID WHERE QuantityInStock < ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                Inventory inventory = new Inventory(
                        rs.getInt("InventoryID"),
                        product,
                        rs.getInt("QuantityInStock"),
                        rs.getDate("LastStockUpdate").toLocalDate()
                );
                lowStock.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStock;
    }
}