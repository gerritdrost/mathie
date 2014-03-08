package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.FunctionOperator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Comparator;

public class SignumOperator
		extends FunctionOperator {

	public SignumOperator() {
		super("sign", Pair.create(1, 1));
	}

	@Override
	public Expression create(final String expressionString, final Expression[] children, final Comparator<Double> comparator) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				value = Math.signum(children[0].getValue());
			}

		};
	}

}
