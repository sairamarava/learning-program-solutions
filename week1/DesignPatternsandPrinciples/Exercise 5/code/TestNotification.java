// TestNotification.java
public class TestNotification {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();

        // Add SMS channel
        notifier = new SMSNotifierDecorator(notifier);

        // Add Slack channel
        notifier = new SlackNotifierDecorator(notifier);

        notifier.send("System maintenance at 10 PM tonight.");
    }
}