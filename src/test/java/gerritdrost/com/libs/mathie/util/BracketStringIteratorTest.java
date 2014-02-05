package gerritdrost.com.libs.mathie.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests if the BracketStringIterator correctly keeps track of the bracket counts.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
@RunWith(JUnit4.class)
public class BracketStringIteratorTest {

	@Test
	public void negativeBracketCount() {

		String test = ")))((";

		BracketStringIterator iterator = BracketStringIterator.create(test, BracketStringIterator.Direction.LEFT_TO_RIGHT);

		iterator.next();
		assertEquals(-1, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-2, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-3, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-2, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-1, iterator.getOpenedBrackets());

		assertFalse(iterator.hasNext());
	}

	@Test
	public void rightToLeftNegativeCount() {

		String test = ")))((";

		BracketStringIterator iterator = BracketStringIterator.create(test, BracketStringIterator.Direction.RIGHT_TO_LEFT);

		iterator.next();
		assertEquals(-1, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-2, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(-1, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(0, iterator.getOpenedBrackets());
		iterator.next();
		assertEquals(1, iterator.getOpenedBrackets());

		assertFalse(iterator.hasNext());
	}

	@Test
	public void leftToRightExpression() {

		String test = "(a+x)-((a*x)-c)";

		BracketStringIterator iterator = BracketStringIterator.create(test, BracketStringIterator.Direction.LEFT_TO_RIGHT);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while (iterator.hasNext()) {
			iterator.next();

			min = Math.min(min, iterator.getOpenedBrackets());
			max = Math.max(max, iterator.getOpenedBrackets());
		}

		assertEquals(0, min);
		assertEquals(2, max);
	}

	@Test
	public void rightToLeftExpression() {

		String test = "(a+x)-((a*x)-c)";

		BracketStringIterator iterator = BracketStringIterator.create(test, BracketStringIterator.Direction.RIGHT_TO_LEFT);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while (iterator.hasNext()) {
			iterator.next();

			min = Math.min(min, iterator.getOpenedBrackets());
			max = Math.max(max, iterator.getOpenedBrackets());
		}

		assertEquals(0, min);
		assertEquals(2, max);
	}

}
