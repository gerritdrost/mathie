package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.config.MathieGraphConfiguration;
import gerritdrost.com.apps.mathie.config.defaults.DefaultMathieGraphConfiguration;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.node.defaults.Variable;
import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.util.ExpressionUtils;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MathieEnvironment {

	protected Set<Expression> nodes = new HashSet<Expression>();
	protected HashMap<String, Expression> expressionMap = new HashMap<String, Expression>();
	protected HashMap<String, Variable> variableMap = new HashMap<String, Variable>();
	protected MathieGraphConfiguration operatorConfiguration = new DefaultMathieGraphConfiguration();

	public MathieEnvironment() {
		initializeVariables();
	}

	protected void initializeVariables() {

		for (Pair<String, Double> defaultVariables : operatorConfiguration.getDefaultVariables()) {
			Expression variable = getExpression(defaultVariables.getA());

			// hmm, might have to change something, this looks strange
			if (!(variable instanceof Variable))
				continue;

			((Variable) variable).set(defaultVariables.getB());
		}
	}

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

	public Expression getExpression(String expression) {

		expression = ExpressionUtils.normalizeString(expression);

		if (expressionMap.containsKey(expression))
			return expressionMap.get(expression);
		else
			return insertExpression(expression);
	}

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
