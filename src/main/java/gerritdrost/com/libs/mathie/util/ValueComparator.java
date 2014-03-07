package gerritdrost.com.libs.mathie.util;

import java.util.Comparator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueComparator
		implements Comparator<Double> {

	double delta;

	/**
	 * Compares two doubles taking a given delta value into account. Returns 1 when the first parameter is higher than
	 * the second, 0 when they are equals and -1 when the first parameter is lower than the second.
	 */
	@Override
	public int compare(Double o1, Double o2) {

		double diff = o1 - o2;

		boolean equal = Math.abs(diff) < delta;

		return equal ? 0 : diff > 0.0 ? 1 : -1;
	}

}
