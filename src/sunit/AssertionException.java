package sunit;

public final class AssertionException extends RuntimeException {
	private static final long serialVersionUID = -7456631347942613543L;
	
	private boolean passed;
	private String expected;
	private String actual;
	
	public AssertionException(boolean passed, String expected, String actual) {
		this.passed = passed;
		this.expected = expected;
		this.actual = actual;
	}
	
	public boolean isPassed() {
		return passed;
	}

	public String getExpected() {
		return this.expected;
	}

	public String getActual() {
		return this.actual;
	}
}
