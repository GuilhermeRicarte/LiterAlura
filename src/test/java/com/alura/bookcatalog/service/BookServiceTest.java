package com.alura.bookcatalog.service;

import com.alura.bookcatalog.model.Book;
import com.alura.bookcatalog.model.Author;
import com.alura.bookcatalog.repository.BookRepository;
import com.alura.bookcatalog.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author();
        testAuthor.setId(1L);
        testAuthor.setName("Test Author");
        testAuthor.setBirthYear(1900);
        testAuthor.setDeathYear(2000);

        testBook = new Book();
        testBook.setId(1L);
        testBook.setGutendexId(1L);
        testBook.setTitle("Test Book");
        testBook.setDownloadCount(1000);
        testBook.setAuthor(testAuthor);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBook);
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        Book result = bookService.getBookById(999L);

        assertNull(result);
        verify(bookRepository, times(1)).findById(999L);
    }

    @Test
    public void testGetBookByGutendexId() {
        when(bookRepository.findByGutendexId(1L)).thenReturn(Optional.of(testBook));

        Book result = bookService.getBookByGutendexId(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).findByGutendexId(1L);
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorList.add(testAuthor);
        when(authorRepository.findAll()).thenReturn(authorList);

        List<Author> result = bookService.getAllAuthors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Author", result.get(0).getName());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

        Author result = bookService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("Test Author", result.getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTotalBooks() {
        when(bookRepository.count()).thenReturn(100L);

        Long result = bookService.getTotalBooks();

        assertEquals(100L, result);
        verify(bookRepository, times(1)).count();
    }

    @Test
    public void testGetTotalAuthors() {
        when(authorRepository.count()).thenReturn(50L);

        Long result = bookService.getTotalAuthors();

        assertEquals(50L, result);
        verify(authorRepository, times(1)).count();
    }
}
