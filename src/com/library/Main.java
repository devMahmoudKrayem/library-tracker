
package com.library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookManager bookManager = new BookManager();

        BookSearchManager searchManager =
                new BookSearchManager(
                        bookManager.getBooks()
                );

        printBanner();

        int choice;

        do {
            System.out.println("\n========== LIBRARY TRACKER ==========");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");
            System.out.println("=====================================");

            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    if (title.isBlank() || author.isBlank()) {
                        System.out.println("Title and author cannot be empty!");
                    } else {
                        bookManager.addBook(title, author);
                    }
                    break;

                case 2:
                    bookManager.listBooks();
                    break;

                case 3:
                    System.out.println("\n1. Search by Title");
                    System.out.println("2. Search by Author");
                    System.out.print("Choose search type: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid choice!");
                        scanner.nextLine();
                        break;
                    }

                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (searchChoice == 1) {
                        System.out.print("Enter book title: ");
                        String searchTitle = scanner.nextLine();

                        searchManager.searchByTitle(searchTitle);

                    } else if (searchChoice == 2) {
                        System.out.print("Enter author name: ");
                        String searchAuthor = scanner.nextLine();

                        searchManager.searchByAuthor(searchAuthor);

                    } else {
                        System.out.println("Invalid search choice!");
                    }
                    break;

                case 4:
                    System.out.print("Enter book ID to remove: ");

                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        bookManager.removeBook(id);
                    } else {
                        System.out.println("Please enter a valid ID!");
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Library Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        scanner.close();
    }

    public static void printBanner() {
        System.out.println("================================");
        System.out.println("        LIBRARY TRACKER");
        System.out.println("================================");
    }
}
