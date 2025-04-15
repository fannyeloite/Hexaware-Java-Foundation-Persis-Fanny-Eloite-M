package com.hexaware.dao.impl;

import com.hexaware.dao.PaymentDAO;
import com.hexaware.entity.Payment;
import com.hexaware.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
	
	  @Override
	    public void recordPayment(Payment payment) throws SQLException {
	        Connection conn = DBConnector.getConnection();
	        String sql = "INSERT INTO payments (paymentid, orderid, amount, method, status) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, payment.getPaymentId());
	        ps.setInt(2, payment.getOrderId());
	        ps.setDouble(3, payment.getAmount());
	        ps.setString(4, payment.getMethod());
	        ps.setString(5, payment.getStatus());
	        ps.executeUpdate();
	        conn.close();
	    }
	

}
