package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.FunctionOperator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Comparator;

public class EqualsOperator
		extends FunctionOperator {

	public EqualsOperator() {
		super("equals", Pair.create(1, Integer.MAX_VALUE));
	}

	@Override
	public Expression create(final String expressionString, final Expression[] children, final Comparator<Double> comparator) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				double compareVal = children[0].getValue();
				
				value = 1.0;

				for (int i = 1; i < children.length; i++) {
					if (comparator.compare(compareVal, children[i].getValue()) != 0) {
						value = 0.0;
						return;
					}
				}
			}

		};
	}

}
