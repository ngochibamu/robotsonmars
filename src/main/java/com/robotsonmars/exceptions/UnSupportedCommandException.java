package com.robotsonmars.exceptions;

public class UnSupportedCommandException extends Exception {
	
	public UnSupportedCommandException(){}

	public UnSupportedCommandException(String message){
		super(message);
	}

	public UnSupportedCommandException(Throwable cause){
			super(cause);
	}

	public UnSupportedCommandException(String message, Throwable cause){
		super(message, cause);
	}
}