package com.hexaware.exception;

public class PolicyNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PolicyNotFoundException() {
        super("Policy not found!");
    }

    public PolicyNotFoundException(String message) {
        super(message);
    }
}
	


