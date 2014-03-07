package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator;

public class PowerOperator
		extends InfixOperator {

	public PowerOperator() {
		super('^', OperatorAssociativity.RIGHT);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				value = Math.pow(children[0].getValue(), children[1].getValue());
			}

		};
	}
}
