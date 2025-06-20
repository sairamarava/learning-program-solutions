public class ConcreteDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument(String type) {
        if ("WORD".equalsIgnoreCase(type)) {
            return new WordDocument();
        } else if ("PDF".equalsIgnoreCase(type)) {
            return new PdfDocument();
        } else if ("EXCEL".equalsIgnoreCase(type)) {
            return new ExcelDocument();
        }
        throw new IllegalArgumentException("Unknown document type: " + type);
    }
}
