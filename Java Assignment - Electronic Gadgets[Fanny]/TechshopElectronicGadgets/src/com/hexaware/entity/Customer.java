package com.hexaware.entity;

public class Customer {
	    // Attributes
	    private int customerID;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private String address;

	    // Constructor
	    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
	        this.customerID = customerID;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.phone = phone;
	        this.address = address;
	    }

	    // Getters and Setters with validation
	    public int getCustomerID() {
	        return customerID;
	    }

	    public void setCustomerID(int customerID) {
	        this.customerID = customerID;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        if (firstName != null && !firstName.isEmpty()) {
	            this.firstName = firstName;
	        }
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        if (lastName != null && !lastName.isEmpty()) {
	            this.lastName = lastName;
	        }
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        if (email != null && email.contains("@")) {
	            this.email = email;
	        }
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        if (phone != null && phone.matches("\\d{10}")) {
	            this.phone = phone;
	        }
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        if (address != null && !address.isEmpty()) {
	            this.address = address;
	        }
	    }

	    // Methods

	    public void calculateTotalOrders() {
	        // Placeholder - will be implemented when DAO is connected
	        System.out.println("Calculating total orders for customer ID: " + customerID);
	    }

	    public void getCustomerDetails() {
	        System.out.println("Customer ID: " + customerID);
	        System.out.println("Name: " + firstName + " " + lastName);
	        System.out.println("Email: " + email);
	        System.out.println("Phone: " + phone);
	        System.out.println("Address: " + address);
	    }

	    public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) {
	        setEmail(newEmail);
	        setPhone(newPhone);
	        setAddress(newAddress);
	        System.out.println("Customer info updated successfully.");
	    }
	}
