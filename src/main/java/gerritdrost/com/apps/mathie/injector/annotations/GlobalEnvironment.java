package gerritdrost.com.apps.mathie.injector.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to override the default instance-specific environment
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GlobalEnvironment {
	String value();
}
