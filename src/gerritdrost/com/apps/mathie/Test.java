package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.injector.MathieGraphInjector;
import gerritdrost.com.apps.mathie.injector.annotations.Environment;
import gerritdrost.com.apps.mathie.injector.annotations.Formula;
import gerritdrost.com.apps.mathie.injector.annotations.Var;
import gerritdrost.com.apps.mathie.node.defaults.Variable;

public class Test {

	@Environment
	MathieEnvironment mathie;

	@Var("x")
	Variable x;

	@Formula("2*x")
	Expression expression1;

	@Formula("5*x")
	Expression expression2;

	@Formula("x/2")
	Expression expression3;

	@Formula("2^x")
	Expression expression4;

	public Test() {
		MathieGraphInjector.get()
							.inject(this);

	}

	public static void main(String[] args) {
		Test t = new Test();

		System.out.println(t.x.getValue());
		System.out.println(t.expression1.getValue());
		System.out.println(t.expression2.getValue());
		System.out.println(t.expression3.getValue());
		System.out.println(t.expression4.getValue());

		t.x.set(20.0);

		System.out.println(t.x.getValue());
		System.out.println(t.expression1.getValue());
		System.out.println(t.expression2.getValue());
		System.out.println(t.expression3.getValue());
		System.out.println(t.expression4.getValue());
	}
}
