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
public class EqualsOperatorTest {

	ExpressionEnvironment mathieEnv;
	
	@Before
	public void setup() {
		mathieEnv = new ExpressionEnvironment(new DefaultConfiguration() {

			@Override
			public Collection<Operator> getOrderedOperators() {
				return Arrays.asList(new Operator[] { new EqualsOperator(), new VariableOperator(), new ValueOperator() });
			}

			public Comparator<Double> getComparator() {
				return new ValueComparator(0.1);
			}

		});
	}

	@Test
	public void checkDefault() {
		assertEquals(1.0, mathieEnv.getExpression("equals(1.0, 1.0)")
									.getValue(), 0.0);
	}

	@Test
	public void checkNested() {
		assertEquals(1.0, mathieEnv.getExpression("equals(equals(2.0, 2.0),1.0)")
									.getValue(), 0.0);
	}

	@Test
	public void checkMultiple() {
		assertEquals(1.0, mathieEnv.getExpression("equals(3.0, 3.0, 3.0)")
									.getValue(), 0.0);
	}

	@Test
	public void checkDelta() {

		// delta is 0.1, so 1.0 <--> 1.05 should return 1.0;
		assertEquals(1.0, mathieEnv.getExpression("equals(1.0,1.05)")
									.getValue(), 0.0);

		// delta is 0.1, so 1.0 <--> 1.15 should return 0.0;
		assertEquals(0.0, mathieEnv.getExpression("equals(1.0,1.15)")
									.getValue(), 0.0);
	}

	@After
	public void tearDown() {

	}
}
