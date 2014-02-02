package gerritdrost.com.apps.mathie.injector;

import gerritdrost.com.apps.mathie.ExpressionEnvironment;
import gerritdrost.com.apps.mathie.defaults.expression.Variable;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.injector.annotations.Environment;
import gerritdrost.com.apps.mathie.injector.annotations.Formula;
import gerritdrost.com.apps.mathie.injector.annotations.GlobalEnvironment;
import gerritdrost.com.apps.mathie.injector.annotations.Var;
import gerritdrost.com.apps.mathie.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Looks for Environment,Variable and Formula annotations and injects corresponding instances. By default will create an
 * environment per given object instance. When an environment name is provided as parameter, the environment is
 * stored/looked up in a Map so it can be shared between instances.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class MathieInjector {

	private static MathieInjector injector = null;

	/**
	 * Contains global environments that can be shared between instances. Note that these are only global for this
	 * injector, the variable is not static.
	 */
	private final HashMap<String, ExpressionEnvironment> globalEnvironmentMap = new HashMap<String, ExpressionEnvironment>();

	/**
	 * Creates a new Environment. If you implemented your own MathieEnvironment, override this to return instances of
	 * that class.
	 * 
	 * @return
	 */
	protected ExpressionEnvironment createEnvironment() {
		return new ExpressionEnvironment();
	}

	/**
	 * Gets the global environment with the provided name or creates it if it does not exist yet. Note that these are
	 * only global for this injector, they are not stored in a static variable.
	 * 
	 * @param globalEnvironmentName
	 *            the name of the environment
	 * @return
	 */
	protected ExpressionEnvironment getGlobalEnvironment(String globalEnvironmentName) {

		if (!globalEnvironmentMap.containsKey(globalEnvironmentName))
			globalEnvironmentMap.put(globalEnvironmentName, createEnvironment());

		return globalEnvironmentMap.get(globalEnvironmentName);
	}

	/**
	 * Injects the annotated variables of the given object
	 * 
	 * @param object
	 *            the object whose variables should be injected
	 */
	public void injectFields(Object object) {

		Class clazz = object.getClass();

		ExpressionEnvironment environment = createEnvironment();

		// Class annotations first to determine the default environment to use
		for (Annotation annotation : clazz.getAnnotations()) {

			if (annotation instanceof GlobalEnvironment)
				environment = getGlobalEnvironment(((GlobalEnvironment) annotation).value());

		}

		// Field annotations. We use the ReflectionUtils method so we can also inject fields in superclasses. For some
		// reason Java does not have methods to accomplish this.
		for (Field field : ReflectionUtils.getAllFields(object)) {

			for (Annotation annotation : field.getAnnotations()) {

				if (field.getType()
							.isAssignableFrom(Variable.class) && annotation instanceof Var) {
					injectVariable(environment, (Var) annotation, object, field);
				} else if (field.getType()
								.isAssignableFrom(Expression.class) && annotation instanceof Formula) {
					injectExpression(environment, (Formula) annotation, object, field);
				} else if (field.getType()
								.isAssignableFrom(ExpressionEnvironment.class) && annotation instanceof Environment) {
					injectEnvironment(environment, (Environment) annotation, object, field);
				}

			}
		}

		if (object instanceof MathieInjectable)
			((MathieInjectable) object).injectionDone();

	}

	/**
	 * Injects the given environment into the Field environmentField of the Object object
	 * 
	 * @param environment
	 *            the environment to inject
	 * @param environmentAnnotation
	 *            the annotation that defines the environment to inject
	 * @param object
	 *            the object that contains the field to be injected
	 * @param environmentField
	 *            the field of the object that needs to be injected
	 */
	protected void injectEnvironment(ExpressionEnvironment environment, Environment environmentAnnotation, Object object, Field environmentField) {

		environment = chooseEnvironment(environment, environmentAnnotation.env());

		environmentField.setAccessible(true);

		try {
			environmentField.set(object, environment);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}

	}

	/**
	 * Gets/creates the expression defined in the variable annotation and injects it into the Field expressionField of
	 * the Object object
	 * 
	 * @param environment
	 *            the environment to create the expression in
	 * @param expressionAnnotation
	 *            the annotation that defines the expression
	 * @param object
	 *            the object that contains the field to be injected
	 * @param expressionField
	 *            the field of the object that needs to be injected
	 */
	protected void injectExpression(ExpressionEnvironment environment, Formula expressionAnnotation, Object object, Field expressionField) {

		// Both value() and expr() can be the expression string. Bit of a hack but it makes it possible to have a
		// shorter formula annotation when no other variables need to be defined.
		String expressionString = "";

		if (expressionAnnotation.value()
								.length() == 0)
			expressionString = expressionAnnotation.expr();
		else
			expressionString = expressionAnnotation.value();

		if (expressionString.length() == 0)
			throw new NoFormulaDefinedException(object, expressionField);

		// We know the expression now, injection is next
		environment = chooseEnvironment(environment, expressionAnnotation.env());

		Expression expression = environment.getExpression(expressionString);

		expressionField.setAccessible(true);

		try {
			expressionField.set(object, expression);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}

	}

	/**
	 * Gets/creates the variable defined in the variable annotation and injects it into the Field variableField of the
	 * Object object
	 * 
	 * @param environment
	 *            the environment to create the expression in
	 * @param variableAnnotation
	 *            the annotation that defines the variable
	 * @param object
	 *            the object that contains the field to be injected
	 * @param variableField
	 *            the field of the object that needs to be injected
	 */
	protected void injectVariable(ExpressionEnvironment environment, Var variableAnnotation, Object object, Field variableField) {

		// Both value() and name() can be the variable name. Bit of a hack but it makes it possible to have a
		// shorter variable annotation when no other variables need to be defined.
		String variableName = "";

		if (variableAnnotation.value()
								.length() == 0)
			variableName = variableAnnotation.name();
		else
			variableName = variableAnnotation.value();

		if (variableName.length() == 0)
			throw new NoVariableNameDefinedException(object, variableField);

		// We know the name, injection is next
		environment = chooseEnvironment(environment, variableAnnotation.env());

		Variable variable = environment.getVariable(variableName);
		variable.set(variableAnnotation.val());

		variableField.setAccessible(true);

		try {
			variableField.set(object, variable);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
	}

	/**
	 * Returns {@code environment} when {@code globalEnvironment} is empty, returns the Global MathieEnvironment with
	 * the provided name otherwise.
	 * 
	 * @param environment
	 *            the default {@code MathieEnvironment}
	 * @param globalEnvironmentName
	 *            name of the global environment to load
	 * @return
	 */
	protected ExpressionEnvironment chooseEnvironment(ExpressionEnvironment environment, String globalEnvironmentName) {
		if (globalEnvironmentName.length() == 0)
			return environment;
		else
			return getGlobalEnvironment(globalEnvironmentName);
	}

	/**
	 * Uses a singleton instance of this class to use it to inject the variables into the given objects.
	 * 
	 * @param object
	 *            the object whose variables should be injected
	 */
	public static final void inject(Object... objects) {

		if (injector == null)
			injector = new MathieInjector();

		for (Object object : objects)
			injector.injectFields(object);

	}

	/**
	 * Uses a singleton instance of this class to use it to inject the variables into the given object.
	 * 
	 * @param object
	 *            the object whose variables should be injected
	 */
	public static final void inject(Object object) {

		if (injector == null)
			injector = new MathieInjector();

		injector.injectFields(object);

	}
}
