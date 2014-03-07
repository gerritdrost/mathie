package gerritdrost.com.libs.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.config.Configuration;
import gerritdrost.com.libs.mathie.operator.Operator;
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
public class DivideOperatorTest {

	ExpressionEnvironment mathieEnv;

	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new Configuration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new DivideOperator(), new ValueOperator() });
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
		assertEquals(mathieEnv.getExpression("3/4")
								.getValue(), 0.75, 0.0);
	}

	/**
	 * The expression "16/4/2" should be parsed as "(16/4)/2 = 2", not "16/(4/2) = 8". This is because division has a
	 * left operator associativity.
	 */
	@Test
	public void checkMultiple() {
		assertEquals(2.0, mathieEnv.getExpression("16/4/2")
									.getValue(), 0.0);
	}

	@Test
	public void checkNested() {
		assertEquals(8.0, mathieEnv.getExpression("16/(4/2)")
									.getValue(), 0.0);
	}

	/**
	 * Division by zero should leave us with Double.Infinity
	 */
	@Test
	public void checkDivisionByZero() {
		assertEquals(Double.POSITIVE_INFINITY, mathieEnv.getExpression("2/0")
														.getValue(), 0.0);
	}

	@After
	public void tearDown() {

	}
}
