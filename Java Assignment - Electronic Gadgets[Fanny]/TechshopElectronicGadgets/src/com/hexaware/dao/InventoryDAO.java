package com.hexaware.dao;

import com.hexaware.entity.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    void deleteInventory(int inventoryID);
    Inventory getInventoryById(int inventoryID);
    List<Inventory> getAllInventory();
    List<Inventory> getLowStockInventory(int threshold);
    Inventory getInventoryByProductId(int productId) throws SQLException;

}
