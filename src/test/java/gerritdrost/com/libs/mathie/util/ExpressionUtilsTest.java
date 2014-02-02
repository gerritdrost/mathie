package gerritdrost.com.libs.mathie.util;

import static org.junit.Assert.assertEquals;
import gerritdrost.com.libs.mathie.util.ExpressionUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ExpressionUtilsTest {

	@Test
	public void onlySpaces() {
		assertEquals(ExpressionUtils.normalizeString(" 2 + 3 "), "2+3");
	}

	@Test
	public void spacesAndOuterBrackets() {
		assertEquals(ExpressionUtils.normalizeString(" ( 2 + 3 ) "), "2+3");
	}

	@Test
	public void spacesOuterAndInnerBrackets() {
		assertEquals(ExpressionUtils.normalizeString(" ( (2 + 3) - 4 ) "), "(2+3)-4");
	}

	@Test
	public void doubleOuterBracketsSingleInnerBrackets() {
		assertEquals(ExpressionUtils.normalizeString("(((2+3)-4))"), "(2+3)-4");
	}
}
