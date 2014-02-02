package gerritdrost.com.libs.mathie.operator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RegexOperator
		extends Operator {

	private String regex;

	@Override
	public boolean applies(String expression) {
		return expression.matches(regex);
	}

}
