package mytest.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsOnlyDigits extends TypeSafeMatcher<String> {

	public static Matcher<String> onlyDigits() {
		return new IsOnlyDigits();
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("string with only digits");
	}

	@Override
	protected boolean matchesSafely(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
	 		return false;
		}
	}

}
