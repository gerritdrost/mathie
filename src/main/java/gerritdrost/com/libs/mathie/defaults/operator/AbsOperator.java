package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.FunctionOperator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Comparator;

public class AbsOperator
		extends FunctionOperator {

	public AbsOperator() {
		super("abs", Pair.create(1, 1));
	}

	@Override
	public Expression create(String expressionString, Expression[] children, Comparator<Double> comparator) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				this.value = Math.abs(children[0].getValue());

			}

		};
	}

}
