package gerritdrost.com.libs.mathie.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FunctionOperatorTest {

	FunctionOperator funcOperator;

	@Before
	public void setup() {
		funcOperator = new FunctionOperator("a", Pair.create(2, 4)) {

			@Override
			public Expression create(String expressionString, Expression[] children, Comparator<Double> comparator) {
				return new Expression(expressionString, children) {

					@Override
					public void recalculate() {
					}
				};
			}
		};
	}

	@Test
	public void singleParameterFunction() {
		String expression = "a(2,3)";

		assertTrue(funcOperator.applies(expression));

		String[] parameters = funcOperator.getChildExpressions(expression);

		assertEquals("2", parameters[0]);
		assertEquals("3", parameters[1]);

		assertEquals(2, parameters.length);
	}

	@Test
	public void wrongFunctionName() {
		assertFalse(funcOperator.applies("b(2,3)"));
	}

	@Test
	public void tooFewParameters() {
		assertFalse(funcOperator.applies("a(2)"));
	}

	@Test
	public void tooManyParameters() {
		assertFalse(funcOperator.applies("a(2,3,4,5,6)"));
	}

}
