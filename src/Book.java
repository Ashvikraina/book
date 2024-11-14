import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Book {
    private String title;
    private ArrayList<String> text = new ArrayList<>();

    Book() {}

    public void printlines(int start, int length) {
        System.out.println("Lines " + start + " to " + (start + length) + " of book: " + title);
        for (int i = start; i < start + length; i++) {
            if (i < text.size()) {
                System.out.println(i + ": " + text.get(i));
            } else {
                System.out.println(i + ": line not in book.");
            }
        }
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    int countWords() {
        int wordCount = 0;
        for (String line : text) {
            wordCount += line.split("\\s+").length;
        }
        return wordCount;
    }

    void appendLine(String line) {
        text.add(line);
    }

    String getLine(int lineNumber) {
        if (lineNumber >= 0 && lineNumber < text.size()) {
            return text.get(lineNumber);
        }
        return "";
    }

    int getLineCount() {
        return text.size();
    }

    public void readFromString(String title, String string) {
        this.title = title;
        Scanner scanner = new Scanner(string);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            text.add(line);
        }
        scanner.close();
    }

    // Place readFromUrl here
    public void readFromUrl(String title, String url) {
        this.title = title;
    
        try {
            URL bookUrl = new URL(url);
            Scanner scanner = new Scanner(bookUrl.openStream());
            boolean isBookContent = false;
            boolean titleSet = false;
    
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
    
                // Adjust for this book's markers
                if (line.contains("*** START OF THE PROJECT GUTENBERG EBOOK")) {
                    isBookContent = true;
                    continue;
                } else if (line.contains("*** END OF THE PROJECT GUTENBERG EBOOK")) {
                    isBookContent = false;
                }
    
                if (isBookContent && !line.trim().isEmpty()) {
                    // Set the title from the first meaningful line
                    if (!titleSet) {
                        this.title = line.trim();
                        titleSet = true;
                    }
                    text.add(line);
                }
            }
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : text) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
