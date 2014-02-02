package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator;

public class DivideOperator
		extends InfixOperator {

	public DivideOperator() {
		super('/', OperatorAssociativity.LEFT);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() / children[1].getValue();
			}

		};
	}
}
