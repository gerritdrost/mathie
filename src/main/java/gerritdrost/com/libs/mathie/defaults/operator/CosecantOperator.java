package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.FunctionOperator;
import gerritdrost.com.libs.mathie.util.MathUtils;

public class CosecantOperator
		extends FunctionOperator {

	public CosecantOperator() {
		super("csc", 1);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				this.value = MathUtils.csc(children[0].getValue());
			}

		};
	}

}
