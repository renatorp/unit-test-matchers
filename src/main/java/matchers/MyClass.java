package matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import matchers.aux.INotificationDAO;
import matchers.aux.Notification;
import matchers.aux.NotificationDAO;

public class MyClass {

	private INotificationDAO notificationDAO;
	
	public MyClass(INotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	public static String processString(String str) {
		if (str.startsWith("1")) {
			return String.valueOf(Math.abs(new Random().nextInt()));
		}
		return str;
	}
	
	public Notification getNotification(Date date) {
		Notification lastNotification = this.notificationDAO.getLastNotification();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		if (lastNotification.getCreationDate().after(c.getTime())) {
			return lastNotification;
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		MyClass mc = new MyClass(new NotificationDAO());
		System.out.println(mc.getNotification(new Date()).getCreationDate());
	}
}
