package com.hexaware.dao;

import com.hexaware.entity.Payment;
import java.sql.SQLException;

public interface PaymentDAO {
	void recordPayment(Payment payment) throws SQLException;
	}
	


