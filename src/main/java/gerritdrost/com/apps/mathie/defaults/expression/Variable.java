package gerritdrost.com.apps.mathie.defaults.expression;

import static gerritdrost.com.apps.mathie.MathieConstants.VARIABLE_DEFAULT_VALUE;
import gerritdrost.com.apps.mathie.expression.Expression;
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
		super(new Expression[0]);
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

}
