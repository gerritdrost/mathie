package gerritdrost.com.apps.mathie.config;

import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.Collection;

public interface MathieGraphConfiguration {

	public Collection<Operator> getOrderedOperators();

	public Collection<Pair<String, Double>> getDefaultVariables();

}
