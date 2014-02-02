package gerritdrost.com.apps.mathie.testsuites;

import gerritdrost.com.apps.mathie.util.ExpressionUtilsTest;
import gerritdrost.com.apps.mathie.util.ReflectionUtilsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExpressionUtilsTest.class, ReflectionUtilsTest.class })
public class UtilTestSuite {

}
