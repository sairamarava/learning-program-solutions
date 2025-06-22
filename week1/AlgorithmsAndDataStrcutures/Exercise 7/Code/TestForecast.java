public class TestForecast {
    public static void main(String[] args) {
        double initialValue = 10000.0;
        double growthRate = 0.10; // 10% annual growth
        int years = 5;

        double result = FinancialForecast.forecastValue(initialValue, growthRate, years);
        System.out.printf("Forecasted value after %d years: ₹%.2f\n", years, result);
    }
}
