package sunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public final class Runner {
	
	private Runner() {}

	private enum CheckType {
		CLASS, METHOD
	}
	
	private static boolean hasAnnotation(Object o, String name, CheckType t) {
		boolean hasAnnotation = false;
		Annotation[] annotations;
		
		if(t == CheckType.CLASS)
			annotations = ((Class<?>)o).getAnnotations();
		else
			annotations = ((Method)o).getAnnotations();
		
		for(final Annotation annotation : annotations) {
			if(annotation.annotationType().getName().equals(name))
				hasAnnotation = true;
		}
		
		return hasAnnotation;
	}
	
	private static boolean hasAnnotation(Method m, String name) {
		return hasAnnotation(m, name, CheckType.METHOD);
	}
	
	private static boolean hasAnnotation(Class<?> c, String name) {
		return hasAnnotation(c, name, CheckType.CLASS);
	}
	
	public static TestResult run(Class<?> test) 
			throws IllegalAccessException, IllegalArgumentException, 
					InstantiationException {
		
		if(!hasAnnotation(test, "sunit.annotations.TestClass"))
			return null;
		
		Vector<Result> results = new Vector<>();
		
		Method[] publicMethods = test.getMethods();
		
		@SuppressWarnings("deprecation")
		Object newInstance = test.newInstance();
		
		boolean passed = true;
		
		for(Method method : publicMethods) {
			if(hasAnnotation(method, "sunit.annotations.TestCase")) {
				Result.Values values = null;
				
				boolean singlePassed = true;
				try {
					method.invoke(newInstance);
				} catch(InvocationTargetException invokee) {
					AssertionException e = (AssertionException) invokee.getCause();
					
					if(!e.isPassed()) {
						passed = false;
						singlePassed = false;
					}

					values = new Result.Values(e.getExpected(), e.getActual());
				}
				
				Result result = new Result(method.getName(), values, singlePassed);
				results.add(result);
			}
		}
		
		return new TestResult(passed, test.getName(), results);
	}
}
