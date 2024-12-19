// File: GestionBibliotheque/src/test/java/com/library/StudentServiceTest.java

package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;
    private Connection connection;

    @BeforeEach
    void setUp() {
        connection = mock(Connection.class);
        studentDAO = mock(StudentDAO.class);
        studentService = new StudentService(studentDAO);
    }

    @Test
    void testAddStudent_Success() {
        // Arrange
        Student student = new Student(1, "John Doe");

        // Act
        studentService.addStudent(student);

        // Assert
        verify(studentDAO, times(1)).addStudent(student);
    }

    @Test
    void testDisplayStudents() {
        // Arrange
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student(1, "Alice"));
        mockStudents.add(new Student(2, "Bob"));
        when(studentDAO.getAllStudents()).thenReturn(mockStudents);

        // Act
        studentService.displayStudents();

        // Assert
        verify(studentDAO, times(1)).getAllStudents();
    }

    @Test
    void testFindStudentById_Success() {
        // Arrange
        Student mockStudent = new Student(1, "John Doe");
        when(studentDAO.getStudentById(1)).thenReturn(mockStudent);

        // Act
        Student result = studentService.findStudentById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        verify(studentDAO, times(1)).getStudentById(1);
    }

    @Test
    void testFindStudentById_NotFound() {
        // Arrange
        when(studentDAO.getStudentById(99)).thenReturn(null);

        // Act
        Student result = studentService.findStudentById(99);

        // Assert
        assertNull(result);
        verify(studentDAO, times(1)).getStudentById(99);
    }
}