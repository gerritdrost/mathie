package gerritdrost.com.apps.mathie.config.defaults;

import gerritdrost.com.apps.mathie.config.MathieGraphConfiguration;
import gerritdrost.com.apps.mathie.operator.Operator;
import gerritdrost.com.apps.mathie.operator.defaults.AddOperator;
import gerritdrost.com.apps.mathie.operator.defaults.DivideOperator;
import gerritdrost.com.apps.mathie.operator.defaults.MultiplyOperator;
import gerritdrost.com.apps.mathie.operator.defaults.PowerOperator;
import gerritdrost.com.apps.mathie.operator.defaults.SinOperator;
import gerritdrost.com.apps.mathie.operator.defaults.SubtractOperator;
import gerritdrost.com.apps.mathie.operator.defaults.ValueOperator;
import gerritdrost.com.apps.mathie.operator.defaults.VariableOperator;
import gerritdrost.com.apps.mathie.util.Pair;

import java.util.Arrays;
import java.util.Collection;

public class DefaultMathieGraphConfiguration
		implements MathieGraphConfiguration {

	@Override
	public Collection<Operator> getOrderedOperators() {
		// @formatter:off 
		return Arrays.asList(new Operator[] { 
              new AddOperator(), 
              new SubtractOperator(), 
              new MultiplyOperator(), 
              new DivideOperator(),
              new PowerOperator(), 
              new SinOperator(),
              new ValueOperator(),
              new VariableOperator()
		});
		// @formatter:on 
	}

	@Override
	public Collection<Pair<String, Double>> getDefaultVariables() {
		// @formatter:off 
		return Arrays.asList(
             Pair.t("e", Math.E), 
             Pair.t("E", Math.E), 
             Pair.t("pi", Math.PI), 
             Pair.t("PI", Math.PI)
         );
		// @formatter:on 

	}

}
