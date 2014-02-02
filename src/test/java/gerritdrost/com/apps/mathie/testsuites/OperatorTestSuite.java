package gerritdrost.com.apps.mathie.testsuites;

import gerritdrost.com.apps.mathie.defaults.operator.AddOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.CosineOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.DivideOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.MultiplyOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.PowerOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.SineOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.SubtractOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.TangentOperatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOperatorTest.class, SubtractOperatorTest.class, MultiplyOperatorTest.class, DivideOperatorTest.class,
				PowerOperatorTest.class, SineOperatorTest.class, CosineOperatorTest.class, TangentOperatorTest.class })
public class OperatorTestSuite {

}
