package gerritdrost.com.libs.mathie.operator;

import gerritdrost.com.libs.mathie.expression.Expression;

public abstract class Operator {
	
	public abstract boolean applies(String expression);

	public abstract String[] getChildExpressions(String expression);

	public abstract Expression create(String expressionString, Expression[] children);

}
