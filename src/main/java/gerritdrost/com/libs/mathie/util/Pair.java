package gerritdrost.com.libs.mathie.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A basic tuple implementation that can hold two types
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 * @param <A>
 * @param <B>
 */
@Data
@AllArgsConstructor
public class Pair<A, B> {

	A a;
	B b;

	/**
	 * Sets both variables at once
	 * 
	 * @param a
	 * @param b
	 */
	public void set(A a, B b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * Returns an instance of Pair with the given parameters.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <A, B> Pair<A, B> create(A a, B b) {
		return new Pair<A, B>(a, b);
	}

}
