package com.zalinius.utilities;

public class Debug {
	
	public static void log(String message) {
		System.out.print(message + "\n");
	}
	
	public static void error(String error) {
		System.err.println(error);
	}

}
