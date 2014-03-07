package gerritdrost.com.libs.mathie.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.config.Configuration;
import gerritdrost.com.libs.mathie.defaults.operator.AddOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperator;
import gerritdrost.com.libs.mathie.defaults.operator.ValueOperator;
import gerritdrost.com.libs.mathie.expression.Expression;
import gerritdrost.com.libs.mathie.operator.InfixOperator.OperatorAssociativity;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GroupedInfixOperatorTest {

	ExpressionEnvironment mathieEnv;

	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new Configuration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new GroupedInfixOperator(new AddOperator(), new SubtractOperator()),
														new ValueOperator() });
			}

			@Override
			public Collection<Pair<String, Double>> getDefaultVariables() {
				return new ArrayList<Pair<String, Double>>();
			}

			@Override
			public Comparator<Double> getComparator() {
				return null;
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(4.0, mathieEnv.getExpression("8-4")
									.getValue(), 0.0);
		assertEquals(12.0, mathieEnv.getExpression("8+4")
									.getValue(), 0.0);
	}

	/**
	 * This test would fail if the SubtractOperator has precedence over the AddOperator since this expression in that
	 * case would be parsed as such: "(8+4)-(2+1) = 9". Correct result is "((8+4)-2)+1 = 11"
	 */
	@Test
	public void checkMultiple() {
		assertEquals(11.0, mathieEnv.getExpression("8+4-2+1")
									.getValue(), 0.0);
	}

	/**
	 * This test is the exact opposite of the checkMultipleCheck in that it enforces the "wrong" expression. The
	 * expression should return 9.
	 */
	@Test
	public void checkNested() {
		assertEquals(9.0, mathieEnv.getExpression("(8+4)-(2+1)")
									.getValue(), 0.0);
	}

	/**
	 * When a GroupedInfixOperator is created without InfixOperators provided, a InvalidGroupedInfixOperatorException
	 * should be thrown.
	 */
	@Test(expected = InvalidGroupedInfixOperatorException.class)
	public void testEmptyInfixOperators() {
		GroupedInfixOperator gio = new GroupedInfixOperator();
	}

	/**
	 * When a GroupedInfixOperator is created with InfixOperators with conflicting associativities, a
	 * InvalidGroupedInfixOperatorException should be thrown.
	 */
	@Test(expected = InvalidGroupedInfixOperatorException.class)
	public void testConflictingAssociativities() {
		GroupedInfixOperator gio = new GroupedInfixOperator(new InfixOperatorStub(OperatorAssociativity.LEFT),
															new InfixOperatorStub(OperatorAssociativity.RIGHT));
	}

	@After
	public void tearDown() {

	}

	private static class InfixOperatorStub
			extends InfixOperator {

		public InfixOperatorStub(OperatorAssociativity operatorAssociativity) {
			super('-', operatorAssociativity);
		}

		@Override
		public Expression create(String expressionString, Expression[] children, Comparator<Double> comparator) {
			return null;
		}

	}

}
