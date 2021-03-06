package sunit;

public final class Result {
	
	public static class Values {
		private String expected;
		private String actual;
		
		public Values(String expected, String actual) {
			this.expected = expected;
			this.actual = actual;
		}
		
		public String getExpected() {
			return expected;
		}
		
		public String getActual() {
			return actual;
		}
	}
	
	private String testCase;
	private Values values;
	private boolean passed;

	public Result (String testCase, Values values, boolean passed) {
		this.testCase = testCase;
		this.values = values;
		this.passed = passed;
	}
	
	public String getTestCase() {
		return testCase;
	}
	
	public Values getValues() {
		return values;
	}

	public boolean isPassed() {
		return passed;
	}
}
