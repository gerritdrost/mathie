package gerritdrost.com.libs.mathie.injector;

import java.lang.reflect.Field;

/**
 * This exception should be thrown when a Formula annotation is encountered without an expression defined.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class NoFormulaDefinedException
		extends RuntimeException {
	public NoFormulaDefinedException(Object object, Field expressionField) {
		super(String.format("The Formula Annotation of Field %s in Object %s has no formula defined.", expressionField, object));
	}
}
