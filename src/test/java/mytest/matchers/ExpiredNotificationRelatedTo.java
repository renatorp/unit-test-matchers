package mytest.matchers;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import matchers.aux.Notification;

public class ExpiredNotificationRelatedTo extends TypeSafeMatcher<Notification> {

	private Date date;
	
	public static ExpiredNotificationRelatedTo expiredNotificationRelatedTo(Date date) {
		ExpiredNotificationRelatedTo expiredNotificationRelatedTo = new ExpiredNotificationRelatedTo();
		expiredNotificationRelatedTo.date = date;
		return expiredNotificationRelatedTo;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("expired notification");
	}

	@Override
	protected boolean matchesSafely(Notification item) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return !item.getCreationDate().after(c.getTime());
	}
	
	@Override
	protected void describeMismatchSafely(Notification item, Description mismatchDescription) {
		 mismatchDescription.appendText("notification within valid interval");
	}

}
