package gerritdrost.com.apps.mathie.injector.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used for fields of type MathieEnvironment. MathieInjector will inject the current environment into
 * the
 * variable when encountering this annotation.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Environment {
	String env() default "";
}
