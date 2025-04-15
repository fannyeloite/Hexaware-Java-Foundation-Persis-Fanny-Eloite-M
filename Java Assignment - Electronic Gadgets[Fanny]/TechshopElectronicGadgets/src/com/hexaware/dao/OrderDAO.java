package com.hexaware.dao;

import com.hexaware.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
	  void insertOrder(Order order);
	  double getTotalSales() throws SQLException;

	    void updateOrderStatus(int orderID, String newStatus);
	    void cancelOrder(int orderID);
	    
	    Order getOrderById(int orderID);
	    List<Order> getAllOrders();
	    List<Order> getOrdersByCustomerId(int customerID);
	    
	

}
