package sunit;

import java.util.Vector;

public class TestResult {
	private boolean passed;
	private String testName;
	private Vector<Result> testCases;
	
	public TestResult(boolean passed, String testName, Vector<Result> testCases) {
		this.passed = passed;
		this.testName = testName;
		this.testCases = testCases;
	}

	public boolean isPassed() {
		return passed;
	}

	public String getTestName() {
		return testName;
	}

	public Vector<Result> getTestCases() {
		return testCases;
	}
}
