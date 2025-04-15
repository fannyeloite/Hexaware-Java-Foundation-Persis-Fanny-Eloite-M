package com.hexaware.exception;

public class ConcurrencyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConcurrencyException(String message) {
        super(message);
    }

}
