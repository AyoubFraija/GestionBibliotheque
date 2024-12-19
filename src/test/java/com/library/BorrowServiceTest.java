package com.library;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.*;

public class BorrowServiceTest {

    private BorrowService borrowService;
    private BorrowDAO borrowDAO;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        borrowDAO = mock(BorrowDAO.class);
        bookDAO = mock(BookDAO.class);
        studentDAO = mock(StudentDAO.class);

        borrowService = new BorrowService(borrowDAO);
        borrowService.setBookDAO(bookDAO);
        borrowService.setStudentDAO(studentDAO);
    }

    @Test

    void testBorrowBook_Success() {
        // Arrange
        Student student = new Student(1,"John Doe");
        Book book = new Book("1", "Effective Java", "Joshua Bloch", 2024);
        Date borrowDate = new Date();
        Date returnDate = new Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000)); // 7 days later
        Borrow borrow = new Borrow(1, student, book, borrowDate, returnDate);

        // Act
        borrowService.borrowBook(borrow);

        // Assert
        verify(borrowDAO, times(1)).save(borrow);
    }



}
