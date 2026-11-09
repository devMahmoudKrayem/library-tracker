
package com.library;

import java.util.List;

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

            if (book.getTitle().toLowerCase()
                    .contains(title.trim().toLowerCase())) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found!");
        }
    }

    public void searchByAuthor(String author) {

        boolean found = false;

        for (Book book : books) {

            if (book.getAuthor().toLowerCase()
                    .contains(author.toLowerCase())) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found!");
        }
    }
}
