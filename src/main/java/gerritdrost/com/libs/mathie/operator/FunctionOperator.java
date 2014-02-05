package gerritdrost.com.libs.mathie.operator;

import static gerritdrost.com.libs.mathie.MathieConstants.FUNCTION_PARAMETER_SEPARATOR;
import gerritdrost.com.libs.mathie.util.BracketStringIterator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.ArrayList;

/**
 * FunctionOperator should be extended when creating a function of the format "[function]([parameters])", like sin() or
 * abs().
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public abstract class FunctionOperator
		extends Operator {

	private String functionName;
	private Pair<Integer, Integer> parameterRange;
	private String baseRegex;

	protected FunctionOperator(String functionName, Pair<Integer, Integer> parameterRange) {

		this.functionName = functionName;
		this.parameterRange = parameterRange;

		baseRegex = String.format("^%s\\(.*\\)", functionName);
	}

	@Override
	public boolean applies(String expression) {
		if (!expression.matches(baseRegex))
			return false;

		String parameterString = getParametersPart(expression);

		// an iterator to count the brackets
		BracketStringIterator bracketIterator = BracketStringIterator.create(parameterString, BracketStringIterator.Direction.LEFT_TO_RIGHT);

		int parameters = 1;

		while (bracketIterator.hasNext()) {
			char c = bracketIterator.next();

			if (bracketIterator.getOpenedBrackets() < 0)
				return false;
			else if (c == ',' && bracketIterator.getOpenedBrackets() == 0)
				parameters++;
		}

		return parameterRange.getA() <= parameters && parameterRange.getB() >= parameters;

	}

	protected String getParametersPart(String expression) {
		return expression.substring(functionName.length() + 1, expression.length() - 1);
	}

	protected String[] splitExpression(String expression) {

		// temporary list to store the parameters in
		ArrayList<String> parameters = new ArrayList<>();

		// the string we'll get the parameters from
		String parameterString = getParametersPart(expression);

		// an iterator to count the brackets
		BracketStringIterator bracketIterator = BracketStringIterator.create(parameterString, BracketStringIterator.Direction.LEFT_TO_RIGHT);

		String currentParameter = "";
		while (bracketIterator.hasNext()) {

			char c = bracketIterator.next();

			if (c == FUNCTION_PARAMETER_SEPARATOR && bracketIterator.getOpenedBrackets() == 0) {
				parameters.add(currentParameter);
				currentParameter = "";
			}

			currentParameter += c;
		}

		// Also add the last parameter
		parameters.add(currentParameter);

		return parameters.toArray(new String[0]);
	}

	@Override
	public String[] getChildExpressions(String expression) {
		return splitExpression(expression);
	}
}
