package mytest.matchers;

import org.assertj.core.api.AbstractAssert;

public class IsOnlyDigitsAssert extends AbstractAssert<IsOnlyDigitsAssert, String> {

	public IsOnlyDigitsAssert(String actual) {
		super(actual, IsOnlyDigitsAssert.class);
	}

	public static IsOnlyDigitsAssert assertThatStr(String actual) {
		return new IsOnlyDigitsAssert(actual);
	}
	
	public IsOnlyDigitsAssert isNotOnlyDigits() {
		
		isNotNull();
		
		if (isOnlyDigit(actual)) {
			failWithMessage("Expected NOT to be only digits but was <%s>", actual);
		}
		return this;
	}
	
	public IsOnlyDigitsAssert isOnlyDigits() {
		isNotNull();
		
		if (!isOnlyDigit(actual)) {
			failWithMessage("Expected to be only digits but was <%s>", actual);
		}
		
		return this;
	}

	private boolean isOnlyDigit(String actual) {
		try {
			Integer.parseInt(actual);
			return true;
		} catch (NumberFormatException nfe) {
		}
		return false;
	}
}
