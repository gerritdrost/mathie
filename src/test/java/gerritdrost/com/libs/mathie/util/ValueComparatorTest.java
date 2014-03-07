package gerritdrost.com.libs.mathie.util;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

public class ValueComparatorTest {

	@Test
	public void checkCompareOutput() {
		double checkedDelta = 0.1;

		Comparator<Double> comparator = new ValueComparator(checkedDelta);

		// should obviously be 0
		assertEquals(0, comparator.compare(1.0, 1.0));

		// delta 0.1 does not include diff 0.1
		assertEquals(1, comparator.compare(1.0, 0.9));

		// diff 0.2, should return -1
		assertEquals(1, comparator.compare(0.8, 1.0));

		// delta 0.1 does include diff 0.05
		assertEquals(0, comparator.compare(0.95, 1.0));

		// delta 0.1 does include diff 0.05
		assertEquals(0, comparator.compare(1.0, 0.95));
	}
}
