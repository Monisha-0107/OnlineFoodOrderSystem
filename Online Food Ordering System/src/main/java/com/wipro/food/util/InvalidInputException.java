package com.wipro.food.util;

public class InvalidInputException extends Exception {
	public String toString() {
		return "Invalid Input";
	}
	 public InvalidInputException(String message) {
	        super(message);
	    }
	

}
