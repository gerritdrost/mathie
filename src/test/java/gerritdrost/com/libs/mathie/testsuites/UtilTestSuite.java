package gerritdrost.com.libs.mathie.testsuites;

import gerritdrost.com.libs.mathie.util.ExpressionUtilsTest;
import gerritdrost.com.libs.mathie.util.MathUtilsTest;
import gerritdrost.com.libs.mathie.util.ReflectionUtilsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExpressionUtilsTest.class, ReflectionUtilsTest.class, MathUtilsTest.class })
public class UtilTestSuite {

}
