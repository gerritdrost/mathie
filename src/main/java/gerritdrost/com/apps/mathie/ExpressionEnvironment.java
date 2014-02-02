package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.config.Configuration;
import gerritdrost.com.apps.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.apps.mathie.defaults.expression.Variable;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.util.ExpressionUtils;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides an Environment for formulas and variables to live in.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class ExpressionEnvironment {

	protected Set<Expression> nodes = new HashSet<Expression>();
	protected HashMap<String, Expression> expressionMap = new HashMap<String, Expression>();
	protected HashMap<String, Variable> variableMap = new HashMap<String, Variable>();
	protected Configuration operatorConfiguration = new DefaultConfiguration();

	/**
	 * Constructs the environment, overwriting the default MathieGraphConfiguration
	 * 
	 * @param operatorConfiguration
	 */
	public ExpressionEnvironment(Configuration operatorConfiguration) {
		this.operatorConfiguration = operatorConfiguration;
		initializeVariables();
	}

	/**
	 * Constructs the environment
	 * 
	 * @param operatorConfiguration
	 */
	public ExpressionEnvironment() {
		initializeVariables();
	}

	/**
	 * Initializes all variables as provided by the MathieGraphConfiguration
	 */
	protected void initializeVariables() {

		for (Pair<String, Double> defaultVariables : operatorConfiguration.getDefaultVariables()) {
			Expression variable = getExpression(defaultVariables.getA());

			// hmm, might have to change something, this looks strange
			if (!(variable instanceof Variable))
				continue;

			((Variable) variable).set(defaultVariables.getB());
		}
	}

	/**
	 * Returns the variable with the provided name. If it does not exist yet, the variable is created.
	 * 
	 * @param variableName
	 *            the name of the variable
	 * @return
	 */
	public Variable getVariable(String variableName) {

		if (variableMap.containsKey(variableName))
			return variableMap.get(variableName);

		Expression variable = getExpression(variableName);

		// hmm, might have to change something, this looks strange
		if (!(variable instanceof Variable))
			return null;

		variableMap.put(variableName, (Variable) variable);

		return (Variable) variable;
	}

	/**
	 * Returns the expression defined by the given String. If the expression does not exist yet, the variable is
	 * created.
	 * 
	 * @param expression
	 *            the String that defines the expression
	 * @return
	 */
	public Expression getExpression(String expression) {

		expression = ExpressionUtils.normalizeString(expression);

		if (expressionMap.containsKey(expression))
			return expressionMap.get(expression);
		else
			return insertExpression(expression);
	}

	/**
	 * Inserts the expression into the graph. Should only be called when the expression does not exist yet.
	 * 
	 * @param expression
	 *            the String that defines the expression
	 * @return
	 */
	protected Expression insertExpression(String expression) {

		expression = ExpressionUtils.normalizeString(expression);

		Expression newNode = null;

		for (Operator operator : operatorConfiguration.getOrderedOperators()) {
			if (operator.applies(expression)) {

				String[] childExpressions = operator.getChildExpressions(expression);
				int childCount = childExpressions.length;
				Expression[] childNodes = new Expression[childCount];

				for (int i = 0; i < childCount; i++) {
					String childExpression = childExpressions[i];
					childNodes[i] = getExpression(childExpression);
				}

				newNode = operator.create(expression, childNodes);
				break;
			}
		}

		if (newNode == null)
			throw new UndefinedExpressionException();

		nodes.add(newNode);
		expressionMap.put(expression, newNode);

		if (newNode instanceof Variable)
			variableMap.put(expression, (Variable) newNode);

		return newNode;
	}

}
