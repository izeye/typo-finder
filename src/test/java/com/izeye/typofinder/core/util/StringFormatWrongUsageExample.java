package com.izeye.typofinder.core.util;

/**
 * Wrong usage example for testing {@link StringFormatValidationUtils}.
 *
 * @author Johnny Lim
 */
public class StringFormatWrongUsageExample {

	public static void main(String[] args) {
		String.format("No format specifier");
		String.format("No format specifier but one parameter.", 1);
		String.format("One format specifier but no parameter: %d");
		String.format("One format specifier but two parameters: %d", 1, "Hello, world!");
	}

}
