package gerritdrost.com.libs.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.config.Configuration;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperator;
import gerritdrost.com.libs.mathie.defaults.operator.ValueOperator;
import gerritdrost.com.libs.mathie.operator.Operator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SubtractOperatorTest {

	ExpressionEnvironment mathieEnv;

	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new Configuration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new SubtractOperator(), new ValueOperator() });
			}

			@Override
			public Collection<Pair<String, Double>> getDefaultVariables() {
				return new ArrayList<Pair<String, Double>>();
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(6.0, mathieEnv.getExpression("8-2")
									.getValue(), 0.0);
	}

	/**
	 * Check with a nested expression that does not conform with the operator associativity
	 */
	@Test
	public void checkNested() {
		assertEquals(6.0, mathieEnv.getExpression("8-(4-2)")
									.getValue(), 0.0);
	}

	/**
	 * The expression "2-3-1" should be parsed as "(2-3)-1", not "2-(3-1)". This is because subtraction has a left
	 * operator associativity.
	 */
	@Test
	public void checkMultiple() {
		assertEquals(mathieEnv.getExpression("2-3-1")
								.getValue(), -2.0, 0.0);
	}

	/**
	 * The SubtractOperator also handles the parsing of negative values by inserting a zero when encountering an empty
	 * string as first argument. This checks if it works.
	 */
	@Test
	public void checkMinusSign() {
		assertEquals(mathieEnv.getExpression("-1")
								.getValue(), -1.0, 0.0);
	}

	@After
	public void tearDown() {

	}
}
