// Computer.java
public class Computer {
    // Required
    private final String cpu;
    private final String ram;

    // Optional
    private final String storage;
    private final String graphicsCard;
    private final String wifiCard;

    // Private constructor using builder
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.wifiCard = builder.wifiCard;
    }

    // Static nested Builder class
    public static class Builder {
        private final String cpu;
        private final String ram;

        private String storage;
        private String graphicsCard;
        private String wifiCard;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder wifiCard(String wifiCard) {
            this.wifiCard = wifiCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer {" +
                "CPU='" + cpu + '\'' +
                ", RAM='" + ram + '\'' +
                ", Storage='" + storage + '\'' +
                ", GraphicsCard='" + graphicsCard + '\'' +
                ", WifiCard='" + wifiCard + '\'' +
                '}';
    }
}