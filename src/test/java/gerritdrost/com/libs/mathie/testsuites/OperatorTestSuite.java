package gerritdrost.com.libs.mathie.testsuites;

import gerritdrost.com.libs.mathie.defaults.operator.AbsOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.AddOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.CosecantOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.CosineOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.CotangentOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.DivideOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.MaximumOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.MinimumOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.MultiplyOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.PowerOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.SecantOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.SineOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.SubtractOperatorTest;
import gerritdrost.com.libs.mathie.defaults.operator.TangentOperatorTest;
import gerritdrost.com.libs.mathie.operator.FunctionOperatorTest;
import gerritdrost.com.libs.mathie.operator.GroupedInfixOperatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddOperatorTest.class, SubtractOperatorTest.class, MultiplyOperatorTest.class, DivideOperatorTest.class,
				PowerOperatorTest.class, SineOperatorTest.class, CosineOperatorTest.class, TangentOperatorTest.class,
				SecantOperatorTest.class, CosecantOperatorTest.class, CotangentOperatorTest.class, GroupedInfixOperatorTest.class,
				AbsOperatorTest.class, FunctionOperatorTest.class, MinimumOperatorTest.class, MaximumOperatorTest.class })
public class OperatorTestSuite {

}
