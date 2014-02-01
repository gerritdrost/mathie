package gerritdrost.com.apps.mathie.expression;

import java.util.HashSet;

import lombok.Getter;

public abstract class Expression {

	@Getter
	protected double value;
	protected Expression[] children = new Expression[0];
	protected HashSet<Expression> parents = new HashSet<Expression>();

	public Expression(Expression[] children) {

		this.children = children;

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

		if (oldValue != newValue)
			updateParents();

	}


	public abstract void recalculate();
}
