package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.InfixOperator;

public class SubtractOperator
		extends InfixOperator {

	public SubtractOperator() {
		super('-');
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Expression(children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() - children[1].getValue();
			}
			
		};
	}

}