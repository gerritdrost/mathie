package gerritdrost.com.libs.mathie.injector;

import java.lang.reflect.Field;

/**
 * This exception should be thrown when a Variable annotation is encountered without a variable name defined.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class NoVariableNameDefinedException
		extends RuntimeException {

	public NoVariableNameDefinedException(Object object, Field variableField) {
		super(String.format("The Variable Annotation of Field %s in Object %s has no variable name defined.", variableField, object));
	}

}
