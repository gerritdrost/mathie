package gerritdrost.com.apps.mathie.operator.defaults;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.FunctionOperator;

public class SinOperator
		extends FunctionOperator {

	public SinOperator() {
		super("sin", 1);
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Expression(children) {

			@Override
			public void recalculate() {
				this.value = Math.sin(children[0].getValue());

			}

		};
	}

}
