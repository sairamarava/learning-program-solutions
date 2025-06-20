public class TestFactoryPattern {
    public static void main(String[] args) {
        DocumentFactory factory = new ConcreteDocumentFactory();

        Document doc1 = factory.createDocument("WORD");
        doc1.open();
        doc1.save();

        Document doc2 = factory.createDocument("PDF");
        doc2.open();
        doc2.save();

        Document doc3 = factory.createDocument("EXCEL");
        doc3.open();
        doc3.save();
    }
}
