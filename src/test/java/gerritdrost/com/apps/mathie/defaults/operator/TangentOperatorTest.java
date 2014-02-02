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
public class TangentOperatorTest {

	ExpressionEnvironment mathieEnv;

	private static final double DELTA = 1e-15;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new TangentOperator(), new MultiplyOperator(), new VariableOperator(),
														new ValueOperator() });
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(1.0, mathieEnv.getExpression("tan(0.25*pi)")
									.getValue(), DELTA);
	}

	@Test
	public void checkNested() {
		assertEquals(0.0, mathieEnv.getExpression("tan(tan(0.0))")
									.getValue(), DELTA);
	}

	@After
	public void tearDown() {

	}
}
