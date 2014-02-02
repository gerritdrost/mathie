package gerritdrost.com.apps.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.apps.mathie.ExpressionEnvironment;
import gerritdrost.com.apps.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.apps.mathie.operator.Operator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SineOperatorTest {

	ExpressionEnvironment mathieEnv;

	private static final double DELTA = 1e-15;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new SineOperator(), new VariableOperator(), new ValueOperator() });
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(mathieEnv.getExpression("sin(pi)")
								.getValue(), 0.0, DELTA);
	}

	@Test
	public void checkNested() {
		assertEquals(0.0, mathieEnv.getExpression("sin(sin(pi))")
									.getValue(), DELTA);
	}

	@After
	public void tearDown() {

	}
}
