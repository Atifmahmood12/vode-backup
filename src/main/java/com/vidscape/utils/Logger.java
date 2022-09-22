package com.vidscape.utils;

public class Logger {
	public StringBuilder ErrorLoggerString;
	
	public Logger() {
		ErrorLoggerString = null;
	}

	public String exceptionLogger(String ErrorMessage) {
		
		try {
			ErrorLoggerString.append(ErrorMessage);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ErrorLoggerString.toString();
		
	}  
}

