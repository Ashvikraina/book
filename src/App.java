public class App {
    public static void main(String[] args)
    {
        // Run tests, comment out once they pass.
        // TestSuite.run();

        // Starter book
        Book input = new Book();

        // Read a book from Project Gutenberg (new book URL)
        input.readFromUrl("New Gutenberg Book", "https://www.gutenberg.org/cache/epub/74701/pg74701.txt");

        // Count and report words
        int wordCount = input.countWords();
        System.out.println("Number of words in book: " + wordCount);

        // Translate the book
        Book output = PigLatinTranslator.translate(input);

        // Save translated book to file
        output.writeToFile("NewBook_PigLatin.txt");

        // Print preview lines from original and translated books
        input.printlines(0, 2);
        output.printlines(0, 2);
    }
}
