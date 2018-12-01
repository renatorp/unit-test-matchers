package mytest.aux;

import java.util.Date;

import matchers.aux.INotificationDAO;
import matchers.aux.Notification;

public class NotificationDAOStub implements INotificationDAO {

	private Date date;

	public NotificationDAOStub(Date date) {
		this.date = date;
	}

	@Override
	public Notification getLastNotification() {
		Notification n = new Notification();
		n.setDate(date);
		return n;
	}

}
