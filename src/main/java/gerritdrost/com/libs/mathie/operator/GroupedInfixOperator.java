package gerritdrost.com.libs.mathie.operator;

import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator.OperatorAssociativity;
import gerritdrost.com.libs.mathie.operator.InvalidGroupedInfixOperatorException.ExceptionType;
import gerritdrost.com.libs.mathie.util.Pair;

/**
 * Combines several InfixOperators into a single Operator, therefore making it possible to correctly parse combinations
 * of equal precedence like '+' and '-' or '*' and '/'.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class GroupedInfixOperator
		extends Operator {

	private InfixOperator[] infixOperators;
	private InfixOperator.OperatorAssociativity operatorAssociativity;

	/**
	 * Constructs the operator and checks if the infixOperators have matching associativity.
	 * 
	 * @param infixOperators
	 */
	public GroupedInfixOperator(InfixOperator... infixOperators) {

		if (infixOperators.length == 0)
			throw new InvalidGroupedInfixOperatorException(ExceptionType.NO_OPERATORS_PROVIDED, null);

		this.infixOperators = infixOperators;

		operatorAssociativity = null;

		for (InfixOperator infixOperator : infixOperators) {

			if (operatorAssociativity == null)
				operatorAssociativity = infixOperator.operatorAssociativity;
			else if (infixOperator.operatorAssociativity != operatorAssociativity)
				throw new InvalidGroupedInfixOperatorException(ExceptionType.ASSOCIATIVITY_MATCH_ERROR, infixOperators);

		}

	}

	@Override
	public boolean applies(String expressionString) {

		for (InfixOperator infixOperator : infixOperators)
			if (infixOperator.applies(expressionString))
				return true;

		return false;

	}

	@Override
	public String[] getChildExpressions(String expressionString) {
		return getDominatingOperator(expressionString).getA()
														.getChildExpressions(expressionString);
	}

	@Override
	public Expression create(String expressionString, Expression[] children) {
		return getDominatingOperator(expressionString).getA()
														.create(expressionString, children);
	}

	/**
	 * Returns the dominating operator, which, depending on the associativity is either the first or the last
	 * operator encountered in the string.
	 * 
	 * @param expressionString
	 * @return
	 */
	protected Pair<InfixOperator, Integer> getDominatingOperator(String expressionString) {

		Pair<InfixOperator, Integer> dominatingOperator = new Pair<InfixOperator, Integer>(
																							null,
																							operatorAssociativity == OperatorAssociativity.LEFT	? Integer.MIN_VALUE
																																				: Integer.MAX_VALUE);

		for (InfixOperator infixOperator : infixOperators) {
			if (infixOperator.applies(expressionString)) {

				int operatorIndex = infixOperator.findOperator(expressionString);

				if ((operatorAssociativity == OperatorAssociativity.LEFT && operatorIndex > dominatingOperator.getB())
					|| (operatorAssociativity == OperatorAssociativity.RIGHT && operatorIndex < dominatingOperator.getB()))
					dominatingOperator.set(infixOperator, operatorIndex);
			}
		}

		return dominatingOperator;

	}

}
