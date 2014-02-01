package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.defaults.expression.Variable;
import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.injector.MathieInjectable;
import gerritdrost.com.apps.mathie.injector.MathieInjector;
import gerritdrost.com.apps.mathie.injector.annotations.Environment;
import gerritdrost.com.apps.mathie.injector.annotations.Formula;
import gerritdrost.com.apps.mathie.injector.annotations.Var;

public class Testie
		implements MathieInjectable {

	@Environment
	MathieEnvironment mathie;

	@Var("x")
	Variable x;

	@Formula("x*pi")
	Expression expression;

	public Testie() {
		MathieInjector.inject(this);
	}

	@Override
	public void injectionDone() {
	}

	public static void main(String[] args) {
		Testie t = new Testie();

		t.x.set(3.0);

		Variable x = t.mathie.getVariable("x");

		System.out.println(t.x == x);

		System.out.println(t.expression.getValue());
	}
}
