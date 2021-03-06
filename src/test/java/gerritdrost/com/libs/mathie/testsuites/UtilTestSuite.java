package gerritdrost.com.libs.mathie.testsuites;

import gerritdrost.com.libs.mathie.util.BracketStringIteratorTest;
import gerritdrost.com.libs.mathie.util.ExpressionUtilsTest;
import gerritdrost.com.libs.mathie.util.MathUtilsTest;
import gerritdrost.com.libs.mathie.util.ReflectionUtilsTest;
import gerritdrost.com.libs.mathie.util.ValueComparatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExpressionUtilsTest.class, ReflectionUtilsTest.class, MathUtilsTest.class, BracketStringIteratorTest.class,
				ValueComparatorTest.class })
public class UtilTestSuite {

}
