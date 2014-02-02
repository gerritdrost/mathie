package gerritdrost.com.apps.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.apps.mathie.MathieEnvironment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SubtractOperatorTest {

	MathieEnvironment mathieEnv;

	@Before
	public void setup() {
		mathieEnv = new MathieEnvironment();
	}

	@Test
	public void checkSubtractOperator() {
		assertEquals(mathieEnv.getExpression("2-3")
								.getValue(), -1.0, 0.0);

		assertEquals(mathieEnv.getExpression("2-3-1")
								.getValue(), -2.0, 0.0);

		assertEquals(mathieEnv.getExpression("(6-3)-1")
								.getValue(), 2.0, 0.0);
	}

	@Test
	public void checkMinusSign() {

		assertEquals(mathieEnv.getExpression("-1")
								.getValue(), -1.0, 0.0);

	}

	@After
	public void tearDown() {

	}
}
