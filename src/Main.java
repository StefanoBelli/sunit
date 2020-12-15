import sunit.Result;
import sunit.Runner;
import sunit.TestResult;

public class Main {
	
	public static void printFinalResults(TestResult tRes) {
		System.out.println("---------");
		System.out.println("T E S T S");
		System.out.println("---------");
		System.out.print("\nTested: ");
		System.out.print(tRes.getTestName());
		System.out.print(" with ");
		System.out.print(tRes.isPassed() ? "successful" : "negative");
		System.out.println(" outcome, details below:\n");
		
		for(final Result testCaseResult : tRes.getTestCases()) {
			System.out.print("\t-->");
			System.out.println(testCaseResult.getTestCase());
			Result.Values values = testCaseResult.getValues();
			if(values != null) {
				String expected = values.getExpected();
				String actual = values.getActual();
				System.out.print("\texpected: ");
				System.out.print(expected);
				System.out.print(", actual: ");
				System.out.println(actual);
				System.out.print("\tpassed? ");
				System.out.println(testCaseResult.isPassed());
				System.out.println();
			} else {
				System.out.println("\tannotated with @TestCase, but no assertion detected\n");
			}
		}
	}
	
	public static void main(String[] args) 
			throws IllegalAccessException, IllegalArgumentException, 
				InstantiationException {
		
		String testClassPkg = null;
		
		for(int i = 0; i < args.length; ++i) {
			if(args[i].equals("-testclass")) {
				if(i + 1 == args.length) {
					System.err.println("-testclass requires an argument");
					return;
				} else {
					i += 1;
					testClassPkg = args[i];
				}
			} else {
				System.err.println(
						new StringBuilder()
						.append("ignoring \"")
						.append(args[i])
						.append("\": unknown argument")
						.toString());
			}
		}
		
		if(testClassPkg == null) {
			System.err.println("-testclass <arg> is required");
			return;
		}
		
		TestResult res;
		try {
			res = Runner.run(Class.forName(testClassPkg));
		} catch(ClassNotFoundException e) {
			System.err.println(new StringBuilder().append("unable to find class: ").append(testClassPkg).toString());
			return;
		}
		
		if(res != null)
			printFinalResults(res);
		else {
			System.err.println(new StringBuilder().append(testClassPkg).append(" is not a valid test class").toString());
		}
	}
	
}
