package gerritdrost.com.libs.mathie.defaults.expression;

import gerritdrost.com.libs.mathie.expression.Expression;

public class Value
		extends Expression {

	public Value(String expressionString, double value) {
		super(expressionString, new Expression[0]);
		this.value = value;
	}

	@Override
	public void recalculate() {

	}

}
