package gerritdrost.com.libs.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.libs.mathie.operator.Operator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CosecantOperatorTest {

	ExpressionEnvironment mathieEnv;

	private static final double DELTA = 1e-15;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new CotangentOperator(), new MultiplyOperator(), new VariableOperator(),
														new ValueOperator() });
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(0.0, mathieEnv.getExpression("cot(0.5*pi)")
									.getValue(), DELTA);
	}

	@After
	public void tearDown() {

	}
}
