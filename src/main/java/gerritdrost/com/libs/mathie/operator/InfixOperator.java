package gerritdrost.com.libs.mathie.operator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class InfixOperator
		extends Operator {

	char operatorChar;
	OperatorAssociativity operatorAssociativity;

	@Override
	public boolean applies(String expression) {
		return ((operatorAssociativity == OperatorAssociativity.LEFT ? findLeftOperator(expression) : findRightOperator(expression)) > -1);
	}

	protected int findLeftOperator(String expression) {

		int openBrackets = 0;

		for (int i = expression.length() - 1; i >= 0; i--) {

			char c = expression.charAt(i);

			if (c == ')')
				openBrackets++;
			else if (c == '(')
				openBrackets--;
			else if (openBrackets == 0 && c == operatorChar)
				return i;
		}

		return -1;
	}

	protected int findRightOperator(String expression) {

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

		int operatorIndex = operatorAssociativity == OperatorAssociativity.LEFT	? findLeftOperator(expression)
																				: findRightOperator(expression);

		return new String[] { expression.substring(0, operatorIndex), expression.substring(operatorIndex + 1, expression.length()) };

	}

	public static enum OperatorAssociativity {
		LEFT,
		RIGHT;
	}

}
