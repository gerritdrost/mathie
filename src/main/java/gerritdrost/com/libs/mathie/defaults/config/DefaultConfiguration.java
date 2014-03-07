package gerritdrost.com.libs.mathie.defaults.config;

import gerritdrost.com.libs.mathie.config.Configuration;
import gerritdrost.com.libs.mathie.defaults.operator.AbsOperator;
import gerritdrost.com.libs.mathie.defaults.operator.AddOperator;
import gerritdrost.com.libs.mathie.defaults.operator.CosecantOperator;
import gerritdrost.com.libs.mathie.defaults.operator.CosineOperator;
import gerritdrost.com.libs.mathie.defaults.operator.CotangentOperator;
import gerritdrost.com.libs.mathie.defaults.operator.DivideOperator;
import gerritdrost.com.libs.mathie.defaults.operator.MaximumOperator;
import gerritdrost.com.libs.mathie.defaults.operator.MinimumOperator;
import gerritdrost.com.libs.mathie.defaults.operator.MultiplyOperator;
import gerritdrost.com.libs.mathie.defaults.operator.PowerOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SecantOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SineOperator;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperator;
import gerritdrost.com.libs.mathie.defaults.operator.TangentOperator;
import gerritdrost.com.libs.mathie.defaults.operator.ValueOperator;
import gerritdrost.com.libs.mathie.defaults.operator.VariableOperator;
import gerritdrost.com.libs.mathie.operator.GroupedInfixOperator;
import gerritdrost.com.libs.mathie.operator.Operator;
import gerritdrost.com.libs.mathie.util.Pair;
import gerritdrost.com.libs.mathie.util.ValueComparator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Default MathieGraphConfiguration implementation.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class DefaultConfiguration
		implements Configuration {

	Comparator<Double> comparator = new ValueComparator(1e-15);

	@Override
	public Collection<Operator> getOrderedOperators() {
		// @formatter:off 
		return Arrays.asList(new Operator[] { 
              new GroupedInfixOperator(
                  new AddOperator(), 
                  new SubtractOperator()
              ),
              new GroupedInfixOperator(
	              new MultiplyOperator(), 
	              new DivideOperator()
              ),
              new PowerOperator(), 
              new AbsOperator(),
              new SineOperator(),
              new CosineOperator(),
              new SecantOperator(),
              new CosecantOperator(),
              new TangentOperator(),
              new CotangentOperator(),
              new MaximumOperator(),
              new MinimumOperator(),
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

	@Override
	public Comparator<Double> getComparator() {
		return comparator;
	}

}
