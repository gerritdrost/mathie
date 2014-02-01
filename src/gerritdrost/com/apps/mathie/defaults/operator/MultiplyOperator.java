package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.InfixOperator;

public class MultiplyOperator
		extends InfixOperator {

	public MultiplyOperator() {
		super('*');
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Expression(children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() * children[1].getValue();
			}

		};
	}
}
