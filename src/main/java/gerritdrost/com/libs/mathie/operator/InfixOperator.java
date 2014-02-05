package gerritdrost.com.libs.mathie.operator;

import gerritdrost.com.libs.mathie.util.BracketStringIterator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The infix operator can be extended for use by operators in infix-style like addition, subtraction, multiplication,
 * etc. Given a operator-character, this class will do everything except calculate the actual value since that is
 * operator specific.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
@AllArgsConstructor
public abstract class InfixOperator
		extends Operator {

	@Getter
	char operatorChar;

	@Getter
	OperatorAssociativity operatorAssociativity;

	@Override
	public boolean applies(String expression) {
		return (findOperator(expression) > -1);
	}

	protected int findOperator(String expression) {

		BracketStringIterator iterator = BracketStringIterator.create(	expression,
																		operatorAssociativity == OperatorAssociativity.LEFT	? BracketStringIterator.Direction.RIGHT_TO_LEFT
																															: BracketStringIterator.Direction.LEFT_TO_RIGHT);

		while (iterator.hasNext()) {

			char c = iterator.next();

			if (c == operatorChar && iterator.getOpenedBrackets() == 0)
				return iterator.getCurrentIndex();
		}

		return -1;
	}

	@Override
	public String[] getChildExpressions(String expression) {

		int operatorIndex = findOperator(expression);

		return new String[] { expression.substring(0, operatorIndex), expression.substring(operatorIndex + 1, expression.length()) };

	}

	public static enum OperatorAssociativity {
		LEFT,
		RIGHT;
	}

}
