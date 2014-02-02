package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.defaults.expression.Value;
import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.Operator;

public class ValueOperator
		extends Operator {

	@Override
	public boolean applies(String expression) {
		try {
			double val = Double.parseDouble(expression);
			return true;
		} catch (NullPointerException | NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String[] getChildExpressions(String expression) {
		return new String[0];
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Value(expressionString, Double.parseDouble(expressionString));
	}

}
