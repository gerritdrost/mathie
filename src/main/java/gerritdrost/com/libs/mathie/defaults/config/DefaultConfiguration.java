package gerritdrost.com.libs.mathie.defaults.config;

import gerritdrost.com.libs.mathie.config.Configuration;
import gerritdrost.com.libs.mathie.defaults.operator.AddOperator;
import gerritdrost.com.libs.mathie.defaults.operator.DivideOperator;
import gerritdrost.com.libs.mathie.defaults.operator.MultiplyOperator;
import gerritdrost.com.libs.mathie.defaults.operator.PowerOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SineOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperator;
import gerritdrost.com.libs.mathie.defaults.operator.ValueOperator;
import gerritdrost.com.libs.mathie.defaults.operator.VariableOperator;
import gerritdrost.com.libs.mathie.operator.Operator;
import gerritdrost.com.libs.mathie.util.Pair;

import java.util.Arrays;
import java.util.Collection;

/**
 * Default MathieGraphConfiguration implementation.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class DefaultConfiguration
		implements Configuration {

	@Override
	public Collection<Operator> getOrderedOperators() {
		// @formatter:off 
		return Arrays.asList(new Operator[] { 
              new AddOperator(), 
              new SubtractOperator(), 
              new MultiplyOperator(), 
              new DivideOperator(),
              new PowerOperator(), 
              new SineOperator(),
              new ValueOperator(),
              new VariableOperator()
		});
		// @formatter:on 
	}

	@Override
	public Collection<Pair<String, Double>> getDefaultVariables() {
		// @formatter:off 
		return Arrays.asList(
             Pair.create("e", Math.E), 
             Pair.create("E", Math.E), 
             Pair.create("pi", Math.PI), 
             Pair.create("PI", Math.PI)
         );
		// @formatter:on 

	}

}