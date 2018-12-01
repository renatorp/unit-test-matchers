package matchers.aux;

public class NotificationDAO implements INotificationDAO {

	@Override
	public Notification getLastNotification() {
		return new Notification();
	}

}
