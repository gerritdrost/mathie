package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator;

public class SubtractOperator
		extends InfixOperator {

	public SubtractOperator() {
		super('-', OperatorAssociativity.LEFT);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() - children[1].getValue();
			}

		};
	}

	@Override
	public String[] getChildExpressions(String expression) {

		String[] returnValues = super.getChildExpressions(expression);

		// Little trick to make negative numbers work properly. When a subtraction is found with no number in front of
		// the operator, we pretend there's a zero so it evaluates to a negative number.
		if (returnValues[0].length() == 0)
			returnValues[0] = "0";

		return returnValues;

	}

}
