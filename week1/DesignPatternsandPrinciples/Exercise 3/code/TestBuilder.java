// TestBuilder.java
public class TestBuilder {
    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB").build();

        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9", "32GB")
            .graphicsCard("NVIDIA RTX 4080")
            .storage("2TB SSD")
            .wifiCard("Wi-Fi 6E")
            .build();

        System.out.println("Basic Computer: " + basicComputer);
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}
