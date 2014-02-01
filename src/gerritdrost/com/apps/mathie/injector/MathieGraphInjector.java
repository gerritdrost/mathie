package gerritdrost.com.apps.mathie.injector;

import gerritdrost.com.apps.mathie.MathieEnvironment;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.injector.annotations.Environment;
import gerritdrost.com.apps.mathie.injector.annotations.Formula;
import gerritdrost.com.apps.mathie.injector.annotations.Var;
import gerritdrost.com.apps.mathie.node.defaults.Variable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MathieGraphInjector {

	private static MathieGraphInjector injector = null;

	public static final MathieGraphInjector get() {
		if (injector == null)
			injector = new MathieGraphInjector();

		return injector;
	}

	protected MathieEnvironment createGraph() {
		return new MathieEnvironment();
	}

	public void inject(Object object) {

		Class clazz = object.getClass();

		MathieEnvironment graph = createGraph();

		for (Field field : clazz.getDeclaredFields()) {
			for (Annotation annotation : field.getAnnotations()) {
				if (field.getType()
							.isAssignableFrom(Variable.class) && annotation instanceof Var) {
					injectVariable(graph, (Var) annotation, object, field);
				} else if (field.getType()
								.isAssignableFrom(Expression.class) && annotation instanceof Formula) {
					injectExpression(graph, (Formula) annotation, object, field);
				} else if (field.getType()
								.isAssignableFrom(MathieEnvironment.class) && annotation instanceof Environment) {
					injectEnvironment(graph, object, field);
				}

			}
		}
	}

	protected void injectEnvironment(MathieEnvironment graph, Object object, Field variableField) {

		variableField.setAccessible(true);

		try {
			variableField.set(object, graph);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}

	}

	protected void injectExpression(MathieEnvironment graph, Formula expressionAnnotation, Object object, Field variableField) {

		Expression expression = graph.getExpression(expressionAnnotation.value());
		variableField.setAccessible(true);

		try {
			variableField.set(object, expression);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}

	}

	protected void injectVariable(MathieEnvironment graph, Var variableAnnotation, Object object, Field variableField) {

		Expression expression = graph.getVariable(variableAnnotation.value());
		variableField.setAccessible(true);

		try {
			variableField.set(object, expression);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
	}
}
