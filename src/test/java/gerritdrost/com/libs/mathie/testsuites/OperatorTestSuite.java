package gerritdrost.com.libs.mathie.testsuites;

import gerritdrost.com.libs.mathie.defaults.operator.AddOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.CosineOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.DivideOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.MultiplyOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.PowerOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.SineOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.TangentOperatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOperatorTest.class, SubtractOperatorTest.class, MultiplyOperatorTest.class, DivideOperatorTest.class,
				PowerOperatorTest.class, SineOperatorTest.class, CosineOperatorTest.class, TangentOperatorTest.class })
public class OperatorTestSuite {

}
