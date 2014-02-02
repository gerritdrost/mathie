package gerritdrost.com.apps.mathie.defaults.expression;

import gerritdrost.com.apps.mathie.expression.Expression;

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
