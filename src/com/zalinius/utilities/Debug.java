package com.zalinius.utilities;

public class Debug {
	
	public static void log(Object o) {
		System.out.print(o + "\n");
	}
	
	public static void log(String message) {
		System.out.print(message + "\n");
	}
	
	public static void error(String error) {
		System.err.println(error);
	}
	
	public static void error(Object o) {
		System.err.println(o);
	}

}
