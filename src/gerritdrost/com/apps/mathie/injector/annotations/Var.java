package gerritdrost.com.apps.mathie.injector.annotations;

import gerritdrost.com.apps.mathie.MathieConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used for fields of type Variable. MathieInjector will inject the defined variable into the
 * variable when encountering this annotation.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Var {
	String value() default "";

	String name() default "";
	double val() default MathieConstants.VARIABLE_DEFAULT_VALUE;

	String env() default "";
}
