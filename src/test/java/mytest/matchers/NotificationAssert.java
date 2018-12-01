package mytest.matchers;

import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.AbstractAssert;

import matchers.aux.Notification;

public class NotificationAssert extends AbstractAssert<NotificationAssert, Notification> {

	public NotificationAssert(Notification actual) {
		super(actual, NotificationAssert.class);
	}

	public static NotificationAssert assertThatNotification(Notification actual) {
		return new NotificationAssert(actual);
	}
	
	public NotificationAssert isExpiredNotificationRelatedTo(Date date) {
		isNotNull();
		if (!isExpired(date)) {
			failWithMessage("Expected to be expired by it wasn't  with value <%s> .", date);
		}
		return this;
	}

	public NotificationAssert isNotExpiredNotificationRelatedTo(Date date) {
		isNotNull();
		if (isExpired(date)) {
			failWithMessage("Expected not to be expired by it was with value <%s> .", date);
		}
		return this;
	}
	
	private boolean isExpired(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		boolean isExpired = !actual.getCreationDate().after(c.getTime());
		return isExpired;
	}
	
}
