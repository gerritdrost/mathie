package gerritdrost.com.apps.mathie.operator;

import gerritdrost.com.apps.mathie.Node;

public abstract class Operator {
	
	public abstract boolean applies(String expression);

	public abstract Node create(String expression);

}
