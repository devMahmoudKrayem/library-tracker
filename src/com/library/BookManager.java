
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

        boolean removed = books.removeIf(book -> book.getId() == id);

        if (removed) {
            storage.saveBooks(books);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
    public List<Book> getBooks() {
        return books;
    }
}
