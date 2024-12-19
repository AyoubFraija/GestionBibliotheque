// File: GestionBibliotheque/src/test/java/com/library/BookServiceTest.java

package com.library;

import com.library.service.BookService;
import com.library.dao.BookDAO;
import com.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        bookService.addBook(book);
        verify(bookDAO).add(book);
    }

    @Test
    void testDisplayBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookDAO.getAllBooks()).thenReturn(books);
        bookService.displayBooks();
    }

    @Test
    void testFindBookById() {
        Book book = new Book();
        when(bookDAO.getBookById(1)).thenReturn(book);
        Book result = bookService.findBookById(1);
        assertEquals(book, result);
    }

    @Test
    void testDeleteBook() {
        bookService.deleteBook(1);
        verify(bookDAO).delete(1);
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        bookService.updateBook(book);
        verify(bookDAO).update(book);
    }
}