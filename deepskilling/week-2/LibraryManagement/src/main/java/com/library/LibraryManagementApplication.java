package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        bookService.getAllBooks();
        bookService.addBook("The Great Gatsby");
        bookService.addBook("Clean Code");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
