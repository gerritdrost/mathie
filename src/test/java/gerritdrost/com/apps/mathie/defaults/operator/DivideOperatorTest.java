package gerritdrost.com.apps.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gerritdrost.com.apps.mathie.ExpressionEnvironment;
import gerritdrost.com.apps.mathie.config.Configuration;
import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

		});
	}

	@Test
	public void checkDivideOperator() {
		assertEquals(mathieEnv.getExpression("3/4")
								.getValue(), 0.75, 0.0);

		assertEquals(mathieEnv.getExpression("4/5")
								.getValue(), 0.8, 0.0);

		assertEquals(mathieEnv.getExpression("(20/4)/5")
								.getValue(), 1.0, 0.0);

		assertTrue(Double.isInfinite(mathieEnv.getExpression("2/0")
											.getValue()));

	}

	@After
	public void tearDown() {

	}
}
