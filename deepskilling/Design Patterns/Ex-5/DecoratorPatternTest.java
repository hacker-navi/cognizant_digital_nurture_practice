public class DecoratorPatternTest {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Hello World");
        
        System.out.println("\n--- With SMS Decorator ---");
        Notifier emailWithSMS = new SMSNotifierDecorator(new EmailNotifier());
        emailWithSMS.send("Hello World");
        
        System.out.println("\n--- With Slack and SMS Decorators ---");
        Notifier emailWithSlackAndSMS = new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()));
        emailWithSlackAndSMS.send("Hello World");
        
        System.out.println("\n--- With All Decorators ---");
        Notifier emailWithAllNotifiers = new PushNotifierDecorator(
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier()
                        )
                )
        );
        emailWithAllNotifiers.send("Hello World");
    }
}
