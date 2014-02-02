package gerritdrost.com.libs.mathie.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MathUtilsTest {

	private static final double DELTA = 1e-14;

	@Test
	public void checkSecOutput() {
		assertEquals(1.0, MathUtils.sec(0), DELTA);
		assertEquals(-1.0, MathUtils.sec(Math.PI), DELTA);
		assertEquals(1.0, MathUtils.sec(2 * Math.PI), DELTA);
	}

	@Test
	public void checkCscOutput() {
		assertEquals(1.0, MathUtils.csc(0.5 * Math.PI), DELTA);
		assertEquals(-1.0, MathUtils.csc(1.5 * Math.PI), DELTA);
	}

	@Test
	public void checkCotOutput() {
		assertEquals(0.0, MathUtils.cot(0.5 * Math.PI), DELTA);
		assertEquals(0.0, MathUtils.cot(1.5 * Math.PI), DELTA);
	}
}
