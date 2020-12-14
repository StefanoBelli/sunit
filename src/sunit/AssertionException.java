package sunit;

public final class AssertionException extends RuntimeException {
	private static final long serialVersionUID = -7456631347942613543L;
	
	private boolean passed;
	
	public AssertionException(boolean passed) {
		this.passed = passed;
	}
	
	public boolean isPassed() {
		return passed;
	}
}
