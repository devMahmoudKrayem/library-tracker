
package com.library;

import java.util.List;
import java.util.Locale;

public class BookSearchManager {

    private List<Book> books;

    public BookSearchManager(List<Book> books) {
        this.books = books;
    }

    public void searchByTitle(String title) {

        if (title == null || title.isBlank()) {
            System.out.println("Please enter a book title!");
            return;
        }

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().toLowerCase(Locale.ROOT)
                    .contains(title.trim().toLowerCase(Locale.ROOT))) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found!");
        }
    }

    public void searchByAuthor(String author) {

        if (author == null || author.isBlank()) {
            System.out.println("Please enter an author name!");
            return;
        }

        boolean found = false;

        for (Book book : books) {

            if (book.getAuthor().toLowerCase(Locale.ROOT)
                    .contains(author.trim().toLowerCase(Locale.ROOT))) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found!");
        }
    }
}
