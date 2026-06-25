package com.library.repository;

public class BookRepository {

    public void findAllBooks() {
        System.out.println("Fetching all books from the repository...");
    }

    public void saveBook(String title) {
        System.out.println("Saving book: " + title);
    }
}
