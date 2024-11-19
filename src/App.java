public class App {
    public static void main(String[] args) {
        // Book 1: Translate "Where the West Begins"
        TestSuite.run();
        Book book1 = new Book();
        book1.readFromUrl("Where the West Begins", "https://www.gutenberg.org/cache/epub/74701/pg74701.txt");
        int wordCount1 = book1.countWords();
        System.out.println("Number of words in book 1: " + wordCount1);

        Book translatedBook1 = PigLatinTranslator.translate(book1);
        translatedBook1.writeToFile("WhereTheWestBegins_PigLatin.txt");
        book1.printlines(0, 2);
        translatedBook1.printlines(0, 2);

        // Book 2: Translate "Romeo and Juliet"
        Book book2 = new Book();
        book2.readFromUrl("Romeo and Juliet", "https://www.gutenberg.org/cache/epub/1513/pg1513.txt");
        int wordCount2 = book2.countWords();
        System.out.println("Number of words in book 2: " + wordCount2);

        Book translatedBook2 = PigLatinTranslator.translate(book2);
        translatedBook2.writeToFile("RomeoAndJuliet_PigLatin.txt");
        book2.printlines(0, 2);
        translatedBook2.printlines(0, 2);
    }
}
