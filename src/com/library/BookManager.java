
package com.library;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private List<Book> books;
    private BookStorage storage;

    public BookManager() {
        storage = new BookStorage();
        books = new ArrayList<>();

        books = storage.loadBooks();
    }


    public void addBook(String title, String author) {

        if (title == null || title.isBlank()) {
            System.out.println("Book title is required!");
            return;
        }

        if (author == null || author.isBlank()) {
            System.out.println("Book author is required!");
            return;
        }

        title = title.trim();
        author = author.trim();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)
                    && book.getAuthor().equalsIgnoreCase(author)) {

                System.out.println("This book already exists!");
                return;
            }
        }

        int newId = 1;

        for (Book book : books) {
            if (book.getId() >= newId) {
                newId = book.getId() + 1;
            }
        }

        Book newBook = new Book(newId, title, author);

        books.add(newBook);

        storage.saveBooks(books);

        System.out.println("Book added successfully!");
    }



    public void listBooks() {

        System.out.println("\nTotal Books: " + books.size());

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\n========== BOOK LIST ==========");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("===============================");
    }

    public void removeBook(int id) {

        if (id <= 0) {
            System.out.println("Please enter a valid book ID!");
            return;
        }

        boolean removed = books.removeIf(book -> book.getId() == id);

        if (removed) {
            storage.saveBooks(books);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }    public List<Book> getBooks() {
        return books;
    }
}
