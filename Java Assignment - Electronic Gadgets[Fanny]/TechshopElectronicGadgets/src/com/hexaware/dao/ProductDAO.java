package com.hexaware.dao;

import com.hexaware.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
	void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productID);
    Product getProductById(int productID);
    List<Product> getAllProducts();
    List<Product> searchProductsByName(String keyword) throws SQLException;

}
	

