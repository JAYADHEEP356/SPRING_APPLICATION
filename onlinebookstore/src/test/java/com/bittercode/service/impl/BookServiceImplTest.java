package com.bittercode.service.impl;

import com.bittercode.model.Book;
import com.bittercode.model.StoreException;
import com.bittercode.service.BookService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class BookServiceImplTest {
    
    private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(); // Ensure you have an implementation
    }

    @Test
    public void testAddAndRetrieveBook() throws StoreException {
        Book book = new Book("23456", "Java Basics", "John Doe", 499.99, 10);
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks(); // Corrected method name
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals("Java Basics", books.get(0).getName()); // Use getName() instead of getTitle()
    }
}

