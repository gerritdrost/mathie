package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.FunctionOperator;

public class SineOperator
		extends FunctionOperator {

	public SineOperator() {
		super("sin", 1);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				this.value = Math.sin(children[0].getValue());

			}

		};
	}

}
