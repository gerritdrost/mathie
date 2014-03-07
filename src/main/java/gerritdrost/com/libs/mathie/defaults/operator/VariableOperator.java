package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.defaults.expression.Variable;
import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.RegexOperator;

import java.util.Comparator;

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
	public Expression create(String expression, Expression[] children, Comparator<Double> comparator) {
		return new Variable(expression);
	}

}
