public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }
    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }
}
