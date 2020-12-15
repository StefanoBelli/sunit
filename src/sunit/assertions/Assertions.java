package sunit.assertions;

import sunit.AssertionException;

public final class Assertions {
	private Assertions() {}

	public static void assertTrue(boolean expr) {
		throw new AssertionException(expr, "true", Boolean.toString(expr));
	}
	
	public static void assertFalse(boolean expr) {
		throw new AssertionException(!expr, "false", Boolean.toString(expr));
	}

	public static void assertEquals(String expected, String actual) {
		throw new AssertionException(expected.equals(actual), expected, actual);
	}

	public static void assertEquals(int expected, int actual) {
		throw new AssertionException(expected == actual, Integer.toString(expected), Integer.toString(actual));
	}

	public static void assertEquals(double expected, double actual) {
		throw new AssertionException(expected == actual, Double.toString(expected), Double.toString(actual));
	}
}
