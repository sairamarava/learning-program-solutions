// TestPayment.java
public class TestPayment {
    public static void main(String[] args) {
        // PayPal Payment
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        paypalProcessor.processPayment(150.0);

        // Stripe Payment
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(12000.0);
    }
}
