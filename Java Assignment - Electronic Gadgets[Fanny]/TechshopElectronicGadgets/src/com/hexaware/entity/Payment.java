package com.hexaware.entity;

public class Payment {
	 private int paymentId;
	    private int orderId;
	    private double amount;
	    private String method;
	    private String status;

	    public Payment(int paymentId, int orderId, double amount, String method, String status) {
	        this.paymentId = paymentId;
	        this.orderId = orderId;
	        this.amount = amount;
	        this.method = method;
	        this.status = status;
	    }

	    // Getters and Setters
	    public int getPaymentId() { return paymentId; }
	    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

	    public int getOrderId() { return orderId; }
	    public void setOrderId(int orderId) { this.orderId = orderId; }

	    public double getAmount() { return amount; }
	    public void setAmount(double amount) { this.amount = amount; }

	    public String getMethod() { return method; }
	    public void setMethod(String method) { this.method = method; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }
	}


