
package com.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookStorage {

    private static final String FILE_NAME = "books.json";

    public void saveBooks(List<Book> books) {

        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < books.size(); i++) {

            Book book = books.get(i);

            json.append("  {\n");
            json.append("    \"id\": ").append(book.getId()).append(",\n");
            json.append("    \"title\": \"").append(escape(book.getTitle())).append("\",\n");
            json.append("    \"author\": \"").append(escape(book.getAuthor())).append("\"\n");
            json.append("  }");

            if (i < books.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        try {
            Files.writeString(Path.of(FILE_NAME), json.toString());
            System.out.println("Books saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public List<Book> loadBooks() {

        List<Book> books = new ArrayList<>();

        try {
            Path path = Path.of(FILE_NAME);

            if (!Files.exists(path)) {
                return books;
            }

            String json = Files.readString(path);

            Pattern objectPattern = Pattern.compile("\\{([^{}]*)\\}");
            Matcher objectMatcher = objectPattern.matcher(json);

            while (objectMatcher.find()) {

                String object = objectMatcher.group(1);

                int id = Integer.parseInt(
                        findValue(object, "id", false)
                );

                String title = unescape(
                        findValue(object, "title", true)
                );

                String author = unescape(
                        findValue(object, "author", true)
                );

                books.add(new Book(id, title, author));
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        return books;
    }

    private String findValue(String object, String field, boolean isString) {

        String regex;

        if (isString) {
            regex = "\"" + field + "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"";
        } else {
            regex = "\"" + field + "\"\\s*:\\s*(\\d+)";
        }

        Matcher matcher = Pattern.compile(regex).matcher(object);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private String escape(String value) {

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private String unescape(String value) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {

            char c = value.charAt(i);

            if (c == '\\' && i + 1 < value.length()) {

                char next = value.charAt(++i);

                switch (next) {
                    case 'n':
                        result.append('\n');
                        break;
                    case 'r':
                        result.append('\r');
                        break;
                    case 't':
                        result.append('\t');
                        break;
                    default:
                        result.append(next);
                }

            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
