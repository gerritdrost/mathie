package gerritdrost.com.libs.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.ExpressionEnvironment;
import gerritdrost.com.libs.mathie.defaults.config.DefaultConfiguration;
import gerritdrost.com.libs.mathie.operator.Operator;
import gerritdrost.com.libs.mathie.util.ValueComparator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SignumOperatorTest {

	ExpressionEnvironment mathieEnv;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new SignumOperator(), new VariableOperator(), new ValueOperator() });
			}

			public Comparator<Double> getComparator() {
				return new ValueComparator(0.1);
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(1.0, mathieEnv.getExpression("sign(1.0)")
									.getValue(), 0.0);
	}

	@Test
	public void checkNested() {
		assertEquals(-1.0, mathieEnv.getExpression("sign(sign(-1.0))")
									.getValue(), 0.0);
	}

	@Test
	public void checkMultiple() {
		assertEquals(0.0, mathieEnv.getExpression("sign(0.0)")
									.getValue(), 0.0);
	}

	@After
	public void tearDown() {

	}
}
