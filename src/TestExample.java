import sunit.annotations.TestCase;
import sunit.annotations.TestClass;

import static sunit.assertions.Assertions.*;

@TestClass
public class TestExample {
	
	@TestCase
	public void testCaseA() {
		assertTrue(true);
	}
	
	@TestCase
	public void testCaseB() {
		assertFalse(false);
	}
	
	@TestCase
	public void testCaseC() {
		assertEquals("kiao", "kiao");	
	}

	@TestCase
	public void testCaseD() {
		assertEquals(1,1);
	}

	@TestCase
	public void testCaseE() {
		assertEquals(3.14, 3.14);
	}
}
