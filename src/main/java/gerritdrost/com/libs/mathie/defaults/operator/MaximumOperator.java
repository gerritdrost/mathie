package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.FunctionOperator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Comparator;

public class MaximumOperator
		extends FunctionOperator {

	public MaximumOperator() {
		super("max", Pair.create(2, Integer.MAX_VALUE));
	}

	@Override
	public Expression create(String expressionString, Expression[] children, Comparator<Double> comparator) {
		return new Expression(expressionString, children) {

			@Override
			public void recalculate() {
				double maxValue = Double.NEGATIVE_INFINITY;

				for (Expression child : children)
					if (child.getValue() > maxValue)
						maxValue = child.getValue();

				this.value = maxValue;
			}

		};
	}

}
