package mytest;

import static mytest.matchers.IsOnlyDigits.onlyDigits;
import static mytest.matchers.ExpiredNotificationRelatedTo.expiredNotificationRelatedTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import matchers.MyClass;
import matchers.aux.INotificationDAO;
import matchers.aux.Notification;
import mytest.aux.NotificationDAOStub;

class MyClassTest {

	@Test
	void test_processString_stringWithoutDigits() {
		String result = MyClass.processString("teste");

		assertThat(result, not(is(emptyString())));
		assertThat(result, not(onlyDigits()));
	}

	@Test
	void test_processString_stringWithDigits() {
		String result = MyClass.processString("1teste");

		assertThat(result, not(is(emptyString())));

		// java.lang.AssertionError: Expected: string with only digits but: was "teste"
		assertThat(result, onlyDigits());

		// org.opentest4j.AssertionFailedError: expected: <true> but was: <false>
		assertEquals(true, result.matches("[0-9]*"));
	}

	private INotificationDAO notificationDAOStub;

	@Test
	void test_getNotification_notificatonRetrieved() {
		notificationDAOStub = new NotificationDAOStub(new Date());
		MyClass mc = new MyClass(notificationDAOStub);
		Date notificationSearchDate = new Date();

		Notification notification = mc.getNotification(notificationSearchDate);

		assertThat(notificationDAOStub.getLastNotification(),
				not(expiredNotificationRelatedTo(notificationSearchDate)));
		assertThat(notification, not(nullValue()));
	}

	@Test
	void test_getNotification_notificatonExpired() {
		notificationDAOStub = new NotificationDAOStub(getDateMinusTwoDays());
		MyClass mc = new MyClass(notificationDAOStub);
		Date notificationSearchDate = new Date();

		Notification notification = mc.getNotification(notificationSearchDate);

		assertThat(notificationDAOStub.getLastNotification(), expiredNotificationRelatedTo(notificationSearchDate));
		assertThat(notification, nullValue());
	}

	private Date getDateMinusTwoDays() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_YEAR, -1);
		Date date = c.getTime();
		return date;
	}
}
