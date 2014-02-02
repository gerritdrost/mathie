package gerritdrost.com.apps.mathie.expression;

import java.util.ArrayList;
import java.util.HashSet;

import lombok.Getter;

/**
 * Base class for expressions. Contains some basic logic common for all expressions like child/parent relation
 * management.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public abstract class Expression {

	@Getter
	protected double value;
	protected String expressionString;
	protected Expression[] children = new Expression[0];
	protected HashSet<Expression> parents = new HashSet<Expression>();
	protected ArrayList<ExpressionListener> expressionListeners = new ArrayList<ExpressionListener>();

	public Expression(String expressionString, Expression[] children) {

		this.children = children;

		this.expressionString = expressionString;

		for (Expression child : children)
			child.parents.add(this);

		recalculate();
	}

	protected void updateParents() {

		for (Expression parent : parents)
			parent.update();

	}

	protected void update() {

		double oldValue = value;
		recalculate();
		double newValue = value;

		if (oldValue != newValue) {
			updateParents();
			fireValueChanged(oldValue, newValue);
		}

	}

	protected void fireValueChanged(double oldValue, double newValue) {

		for (ExpressionListener listener : expressionListeners)
			listener.valueChanged(oldValue, newValue);

	}

	public void addExpressionListener(ExpressionListener listener) {
		expressionListeners.add(listener);
	}

	public void removeExpressionListener(ExpressionListener listener) {
		expressionListeners.remove(listener);
	}

	public void dispose() {
		expressionListeners.clear();
		children = null;
		parents = null;
	}

	@Override
	public String toString() {
		return String.format("\"%s\": %g", expressionString, value);
	}

	public abstract void recalculate();
}
