import sunit.annotations.TestCase;
import sunit.annotations.TestClass;

import static sunit.assertions.Assertions.assertTrue;

@TestClass
public class TestExample {
	
	@TestCase
	public void testCaseA() {
		assertTrue(true);
	}
	
	@TestCase
	public void testCaseB() {
		assertTrue(true);
	}
}
