package gerritdrost.com.apps.mathie;

public class UndefinedExpressionException
		extends RuntimeException {

	public UndefinedExpressionException() {
		super("The expression you provided could not be parsed.");
	}

}
