package gerritdrost.com.libs.mathie.util;

public class ExpressionUtils {

	/**
	 * Removes outer brackets from an expression string that do not add any value to the expression anymore.
	 * Examples:
	 * - "(2+3)" becomes "2+3"
	 * - "(2+3)-(1*4)" remains unchanged
	 * 
	 * @param expression
	 * @return
	 */
	public static String removeUnusedBrackets(String expression) {

		if (!expression.matches("^\\(.*\\)$"))
			return expression;

		int bracketsOpen = 1;

		for (int i = 1; i < expression.length() - 1; i++) {
			char c = expression.charAt(i);

			if (c == '(')
				bracketsOpen++;
			else if (c == ')')
				bracketsOpen--;

			if (bracketsOpen == 0)
				return expression;
		}

		if (expression.length() < 3)
			return expression;
		else
			return removeUnusedBrackets(expression.substring(1, expression.length() - 1));

	}

	public static String normalizeString(String expression) {
		return ExpressionUtils.removeUnusedBrackets(expression.replace(" ", ""));
	}
}
