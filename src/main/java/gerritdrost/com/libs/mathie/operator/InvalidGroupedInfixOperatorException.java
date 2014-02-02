package gerritdrost.com.libs.mathie.operator;

public class InvalidGroupedInfixOperatorException
		extends RuntimeException {

	public InvalidGroupedInfixOperatorException(ExceptionType exceptionType, InfixOperator[] infixOperators) {
		super(
				exceptionType == ExceptionType.ASSOCIATIVITY_MATCH_ERROR ? String.format(	"The InfixOperators you provided do not have the same operator associativity and can therefore not be grouped. InfixOperators supplied: %s.",
																							infixOperatorString(infixOperators))
																		: "At least one InfixOperator needs to be provided to create a valid GroupedInfixOperator.");
	}

	public static enum ExceptionType {
		ASSOCIATIVITY_MATCH_ERROR,
		NO_OPERATORS_PROVIDED
	}

	public static String infixOperatorString(InfixOperator[] infixOperators) {
		StringBuilder builder = new StringBuilder();

		for (InfixOperator infixOperator : infixOperators) {
			if (builder.length() > 0)
				builder.append(",");

			builder.append(infixOperator.getClass()
										.getSimpleName());
		}

		return builder.toString();
	}
}
