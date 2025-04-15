package com.hexaware.dao;

import com.hexaware.entity.Customer;
import java.util.List;


public interface CustomerDAO {
	 void insertCustomer(Customer customer);
	    void updateCustomer(Customer customer);
	    void deleteCustomer(int customerID);
	    Customer getCustomerById(int customerID);
	    List<Customer> getAllCustomers();
	}


