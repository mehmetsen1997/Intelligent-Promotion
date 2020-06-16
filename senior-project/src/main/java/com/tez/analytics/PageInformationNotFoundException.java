package com.tez.analytics;

public class PageInformationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2898838883579032130L;

	public PageInformationNotFoundException() {
		super("The page information that you're asking couldn't be found!");
	}
	
	public PageInformationNotFoundException(String message) {
		super(message);
	}
}
