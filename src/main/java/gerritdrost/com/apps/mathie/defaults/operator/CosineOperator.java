package gerritdrost.com.apps.mathie.defaults.operator;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.operator.FunctionOperator;

public class CosineOperator
		extends FunctionOperator {

	public CosineOperator() {
		super("cos", 1);
	}

	@Override
	public Expression create(String expression, Expression[] children) {
		return new Expression(children) {

			@Override
			public void recalculate() {
				this.value = Math.cos(children[0].getValue());

			}

		};
	}

}
