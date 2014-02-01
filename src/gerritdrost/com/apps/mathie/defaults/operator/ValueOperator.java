package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.defaults.expression.Value;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.Operator;

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
	public Expression create(String epxression, Expression[] children) {
		return new Value(Double.parseDouble(epxression));
	}

}
