package gerritdrost.com.apps.mathie.defaults.operator;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.apps.mathie.MathieEnvironment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AddOperatorTest {

	MathieEnvironment mathieEnv;

	@Before
	public void setup() {
		mathieEnv = new MathieEnvironment();
	}

	@Test
	public void checkAddOperator() {
		assertEquals(mathieEnv.getExpression("2+3")
								.getValue(), 5.0, 0.0);

		assertEquals(mathieEnv.getExpression("2+3+1")
								.getValue(), 6.0, 0.0);

		assertEquals(mathieEnv.getExpression("(6+3)+1")
								.getValue(), 10.0, 0.0);
	}

	@After
	public void tearDown() {

	}
}
