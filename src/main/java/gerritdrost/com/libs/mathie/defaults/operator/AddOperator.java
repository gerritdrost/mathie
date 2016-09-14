package gerritdrost.com.libs.mathie.defaults.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator;

import java.util.Comparator;

public class AddOperator
		extends InfixOperator {

	public AddOperator() {
		super('+', OperatorAssociativity.LEFT);
	}

	@Override
	public Expression create(String expression, Expression[] children, Comparator<Double> comparator) {
		return new Expression(expression, children) {

			@Override
			public void recalculate() {
				value = children[0].getValue() + children[1].getValue();/todo review if the flipped expression saves cpu
			}
			
		};
	}

}
