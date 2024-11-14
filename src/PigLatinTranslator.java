public class PigLatinTranslator {
  public static Book translate(Book input) {
      Book translatedBook = new Book();
      translatedBook.setTitle(input.getTitle() + " (Translated to Pig Latin)");

      for (int i = 0; i < input.getLineCount(); i++) {
          String translatedLine = translate(input.getLine(i));
          translatedBook.appendLine(translatedLine);
      }
      return translatedBook;
  }

  public static String translate(String input) {
      String[] words = input.split("(?<=\\s)|(?=\\s)");
      StringBuilder translated = new StringBuilder();

      for (String word : words) {
          if (word.trim().isEmpty()) {
              translated.append(word); // Preserve whitespace
          } else {
              translated.append(translateWord(word));
          }
      }
      return translated.toString();
  }

  private static String translateWord(String input) {
      if (input.isEmpty()) return input;

      // Handle punctuation, hyphens, and alphabet separately
      String[] parts = input.split("(?=[^a-zA-Z])|(?<=[^a-zA-Z])");
      StringBuilder result = new StringBuilder();

      for (String part : parts) {
          if (part.matches("[a-zA-Z]+")) {
              boolean isCapitalized = Character.isUpperCase(part.charAt(0));
              String pigLatinWord;

              if ("aeiouAEIOU".indexOf(part.charAt(0)) != -1) {
                  pigLatinWord = part + "ay";
              } else {
                  int vowelIndex = -1;
                  for (int i = 0; i < part.length(); i++) {
                      if ("aeiouAEIOU".indexOf(part.charAt(i)) != -1) {
                          vowelIndex = i;
                          break;
                      }
                  }
                  pigLatinWord = (vowelIndex == -1)
                      ? part + "ay"
                      : part.substring(vowelIndex) + part.substring(0, vowelIndex) + "ay";
              }

              if (isCapitalized) {
                  pigLatinWord = Character.toUpperCase(pigLatinWord.charAt(0)) + pigLatinWord.substring(1).toLowerCase();
              }

              result.append(pigLatinWord);
          } else {
              result.append(part); // Preserve punctuation and non-alphabetic parts
          }
      }
      return result.toString();
  }
}
