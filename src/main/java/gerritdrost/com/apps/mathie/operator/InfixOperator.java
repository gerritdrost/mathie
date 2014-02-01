package gerritdrost.com.apps.mathie.operator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class InfixOperator
		extends Operator {

	char operatorChar;

	@Override
	public boolean applies(String expression) {
		return (findOperator(expression) > -1);
	}

	protected int findOperator(String expression) {

		int openBrackets = 0;

		for (int i = 0; i < expression.length(); i++) {

			char c = expression.charAt(i);

			if (c == '(')
				openBrackets++;
			else if (c == ')')
				openBrackets--;
			else if (openBrackets == 0 && c == operatorChar)
				return i;
		}

		return -1;
	}

	@Override
	public String[] getChildExpressions(String expression) {

		int operatorIndex = findOperator(expression);

		return new String[] { expression.substring(0, operatorIndex), expression.substring(operatorIndex + 1, expression.length()) };

	}

}
