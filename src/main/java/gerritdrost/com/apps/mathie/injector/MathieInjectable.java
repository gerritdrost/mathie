package gerritdrost.com.apps.mathie.injector;

/**
 * Makes it possible for an object to be notified when all Mathie variables have been injected. Useful to add Listeners
 * to injected variables as this can not be done in the constructor.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public interface MathieInjectable {
	void injectionDone();
}
