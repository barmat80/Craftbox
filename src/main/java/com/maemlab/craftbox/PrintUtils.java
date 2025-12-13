package com.maemlab.craftbox;

import java.util.Enumeration;
import java.util.Properties;

public class PrintUtils {

	private PrintUtils() {
	}

	public static void log(Object o) {
		System.out.print(o);
	}

	public static void logln(Object o) {
		System.out.println(o);
	}

	public static void printProperties(Properties p) {
		Enumeration<?> e = p.propertyNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			logln(key + ": " + p.getProperty(key));
		}
	}
}
