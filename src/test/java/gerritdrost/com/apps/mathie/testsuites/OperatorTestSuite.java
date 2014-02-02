package gerritdrost.com.apps.mathie.testsuites;

import gerritdrost.com.apps.mathie.defaults.operator.AddOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.DivideOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.MultiplyOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.PowerOperatorTest;
import gerritdrost.com.apps.mathie.defaults.operator.SubtractOperatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOperatorTest.class, SubtractOperatorTest.class, MultiplyOperatorTest.class, DivideOperatorTest.class,
				PowerOperatorTest.class })
public class OperatorTestSuite {

}
