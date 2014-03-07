package gerritdrost.com.libs.mathie.config;

import gerritdrost.com.libs.mathie.operator.Operator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Collection;
import java.util.Comparator;

/**
 * Contains methods that provide MathieGraph instances with configuration variables.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public interface Configuration {

	public Collection<Operator> getOrderedOperators();

	public Collection<Pair<String, Double>> getDefaultVariables();

	public Comparator<Double> getComparator();

}
