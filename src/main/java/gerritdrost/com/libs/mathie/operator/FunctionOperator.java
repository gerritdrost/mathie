package gerritdrost.com.libs.mathie.operator;

import static gerritdrost.com.libs.mathie.MathieConstants.FUNCTION_PARAMETER_SEPARATOR;

public abstract class FunctionOperator
		extends Operator {

	private String functionName;
	private int parameterCount;
	private String baseRegex;

	protected FunctionOperator(String functionName, int parameterCount) {

		this.functionName = functionName;
		this.parameterCount = parameterCount;

		StringBuilder builder = new StringBuilder();

		builder.append(String.format("^%s\\(", functionName));

		for (int i = 0; i < parameterCount; i++)
			builder.append(String.format("%s%s", i > 0 ? FUNCTION_PARAMETER_SEPARATOR : "", ".*"));

		builder.append("\\)");

		baseRegex = builder.toString();

	}

	@Override
	public boolean applies(String expression) {
		if (!expression.matches(baseRegex))
			return false;

		String parameterString = expression.substring(functionName.length(), expression.length());

		int bracketCount = 1;

		for (int i = 1; i < parameterString.length() - 1; i++) {
			char c = parameterString.charAt(i);

			if (c == '(')
				bracketCount++;
			if (c == ')')
				bracketCount--;

			if (bracketCount == 0)
				return false;
		}

		return true;

	}

	@Override
	public String[] getChildExpressions(String expression) {

		String parameterString = expression.substring(functionName.length(), expression.length());
		
		return parameterString.split(Character.toString(FUNCTION_PARAMETER_SEPARATOR));

	}
}
