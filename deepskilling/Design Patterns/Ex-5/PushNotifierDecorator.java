public class PushNotifierDecorator extends NotifierDecorator {
    public PushNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending push notification: " + message);
    }
}
