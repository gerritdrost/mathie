package gerritdrost.com.apps.mathie.operator;

import gerritdrost.com.apps.mathie.expression.Expression;

public abstract class Operator {
	
	public abstract boolean applies(String expression);

	public abstract String[] getChildExpressions(String expression);

	public abstract Expression create(String expressionString, Expression[] children);

}
