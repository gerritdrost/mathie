package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.InfixOperator;

public class DivideOperator
		extends InfixOperator {

	public DivideOperator() {
		super('/', OperatorAssociativity.LEFT);
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Expression(children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() / children[1].getValue();
			}

		};
	}
}
