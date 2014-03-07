package gerritdrost.com.libs.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.libs.mathie.defaults.operator.CosineOperator;
import gerritdrost.com.libs.mathie.defaults.operator.MultiplyOperator;
import gerritdrost.com.libs.mathie.defaults.operator.ValueOperator;
import gerritdrost.com.libs.mathie.defaults.operator.VariableOperator;
import gerritdrost.com.libs.mathie.operator.Operator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CosineOperatorTest {

	ExpressionEnvironment mathieEnv;

	private static final double DELTA = 1e-15;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new CosineOperator(), new MultiplyOperator(), new VariableOperator(),
														new ValueOperator() });
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(-1.0, mathieEnv.getExpression("cos(pi)")
									.getValue(), DELTA);
	}

	@Test
	public void checkNested() {
		assertEquals(1.0, mathieEnv.getExpression("cos(cos(0.5*pi))")
									.getValue(), DELTA);
	}

	@After
	public void tearDown() {

	}
}
