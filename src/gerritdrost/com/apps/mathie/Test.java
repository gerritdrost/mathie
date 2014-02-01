package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.expression.Expression;
import gerritdrost.com.apps.mathie.injector.MathieInjector;
import gerritdrost.com.apps.mathie.injector.annotations.Environment;
import gerritdrost.com.apps.mathie.injector.annotations.Formula;
import gerritdrost.com.apps.mathie.injector.annotations.GlobalEnvironment;
import gerritdrost.com.apps.mathie.injector.annotations.Var;
import gerritdrost.com.apps.mathie.node.defaults.Variable;

@GlobalEnvironment("global-1")
public class Test {

	@Environment
	MathieEnvironment mathie;

	@Var(env = "global-2", name = "x", val = 2.0)
	Variable x;

	@Var("x")
	Variable x2;

	@Formula("2*x")
	Expression expression1;

	@Formula("5*x")
	Expression expression2;

	@Formula("x/2")
	Expression expression3;

	@Formula("2^x")
	Expression expression4;

	public Test() {
		MathieInjector.inject(this);

	}

	public static void main(String[] args) {
		Test t = new Test();

		System.out.println(t.x.getValue());
		System.out.println(t.expression1.getValue());
		System.out.println(t.expression2.getValue());
		System.out.println(t.expression3.getValue());
		System.out.println(t.expression4.getValue());

		t.x.set(20.0);
		t.x2.set(3.0);

		System.out.println(t.x.getValue());
		System.out.println(t.expression1.getValue());
		System.out.println(t.expression2.getValue());
		System.out.println(t.expression3.getValue());
		System.out.println(t.expression4.getValue());
	}
}
