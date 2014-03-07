package gerritdrost.com.libs.mathie.injector.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used for fields of type Expression. MathieInjector will inject the defined expression into the
 * variable when encountering this annotation.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Expr {
	String value() default "";
	String expr() default "";
	String env() default "";
}
