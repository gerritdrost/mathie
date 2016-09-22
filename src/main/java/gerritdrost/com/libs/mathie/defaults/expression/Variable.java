package gerritdrost.com.libs.mathie.defaults.expression;

import static gerritdrost.com.libs.mathie.MathieConstants.VARIABLE_DEFAULT_VALUE;
import gerritdrost.com.libs.mathie.expression.Expression;
import lombok.Getter;

/**
 * 
 * @author Gerrit Drost
 * 
 */
public class Variable
		extends Expression {

	@Getter
	protected String variableName;

	public Variable(String variableName) {
		this(variableName, VARIABLE_DEFAULT_VALUE);
	}

	public Variable(String variableName, double value) {
		super(variableName, new Expression[0]);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void recalculate() {

	}

	public void set(double newValue) {

		double oldValue = this.value;
		this.value = newValue;

		updateParents();
		fireValueChanged(oldValue, newValue);
	}

	public void increment(double delta) {
		set(value + delta);
	}
// jo dit is een comment
}
