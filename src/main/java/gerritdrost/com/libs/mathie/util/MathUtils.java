package gerritdrost.com.libs.mathie.util;

/**
 * All methods that are not in java.lang.Math are implemented here.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class MathUtils {

	public static double cot(double a) {
		return 1.0 / Math.tan(a);
	}

	public static double sec(double a) {
		return 1.0 / Math.cos(a);
	}

	public static double csc(double a) {
		return 1.0 / Math.sin(a);
	}

}
