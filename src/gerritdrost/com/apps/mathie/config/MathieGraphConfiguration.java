package gerritdrost.com.apps.mathie.config;

import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.Collection;

/**
 * Contains methods that provide MathieGraph instances with configuration variables.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public interface MathieGraphConfiguration {

	public Collection<Operator> getOrderedOperators();

	public Collection<Pair<String, Double>> getDefaultVariables();

}
