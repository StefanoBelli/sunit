package sunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public final class Runner {
	
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
					InvocationTargetException, InstantiationException {
		
		if(!hasAnnotation(test, "sunit.annotations.TestClass"))
			return null;
		
		Vector<Result> results = new Vector<>();
		
		Method[] publicMethods = test.getMethods();
		
		@SuppressWarnings("deprecation")
		Object newInstance = test.newInstance();
		
		boolean passed = true;
		String expectedTrue = Boolean.toString(true);
		
		for(Method method : publicMethods) {
			if(hasAnnotation(method, "sunit.annotations.TestCase")) {
				Result.Values values = null;
				
				try {
					method.invoke(newInstance);
				} catch(InvocationTargetException invokee) {
					AssertionException e = (AssertionException) invokee.getCause();
					
					boolean res = e.isPassed();
					if(!res) {
						passed = false;
					}
					
					values = new Result.Values(expectedTrue, Boolean.toString(res));
				}
				
				Result result = new Result(method.getName(), values);
				results.add(result);
			}
		}
		
		return new TestResult(passed, test.getName(), results);
	}
}
