package sunit.assertions;

import sunit.AssertionException;

public final class Assertions {
	public static void assertTrue(boolean expr) {
		throw new AssertionException(expr);
	}
}
