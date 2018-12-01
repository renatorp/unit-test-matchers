package mytest;

import static mytest.matchers.IsOnlyDigitsAssert.assertThatStr;
import static mytest.matchers.NotificationAssert.assertThatNotification;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import matchers.MyClass;
import matchers.aux.INotificationDAO;
import matchers.aux.Notification;
import mytest.aux.NotificationDAOStub;

class MyClassTest2 {

	@Test
	void test_processString_stringWithoutDigits() {
		String result = MyClass.processString("teste");

		assertThat(result).isNotEmpty();
		assertThatStr(result).isNotOnlyDigits();
	}

	@Test
	void test_processString_stringWithDigits() {
		String result = MyClass.processString("1teste");

		assertThat(result).isNotEmpty();
		
		// java.lang.AssertionError: Expected: string with only digits but: was "teste"
		assertThatStr(result).isOnlyDigits();
		
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
		
		assertThatNotification(notificationDAOStub.getLastNotification()).isNotExpiredNotificationRelatedTo(notificationSearchDate);
		assertThat(notification).isNotNull();
	}
	
	@Test
	void test_getNotification_notificatonExpired() {
		notificationDAOStub = new NotificationDAOStub(getDateMinusTwoDays());
		MyClass mc = new MyClass(notificationDAOStub);
		Date notificationSearchDate = new Date();
		
		Notification notification = mc.getNotification(notificationSearchDate);
		
		assertThatNotification(notificationDAOStub.getLastNotification()).isExpiredNotificationRelatedTo(notificationSearchDate);
		assertThat(notification).isNull();
	}

	private Date getDateMinusTwoDays() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_YEAR, -1);
		Date date = c.getTime();
		return date;
	}
}
