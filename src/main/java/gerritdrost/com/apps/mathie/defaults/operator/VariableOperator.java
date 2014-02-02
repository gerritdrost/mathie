package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.defaults.expression.Variable;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.RegexOperator;

public class VariableOperator
		extends RegexOperator {

	public VariableOperator() {
		super("^[a-zA-Z]+$");
	}

	@Override
	public String[] getChildExpressions(String expression) {
		return new String[0];
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Variable(expression);
	}

}