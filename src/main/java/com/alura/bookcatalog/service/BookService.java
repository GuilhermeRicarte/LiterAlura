package com.alura.bookcatalog.service;

import com.alura.bookcatalog.model.Book;
import com.alura.bookcatalog.model.Author;
import com.alura.bookcatalog.repository.BookRepository;
import com.alura.bookcatalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book getBookByGutendexId(Long gutendexId) {
        return bookRepository.findByGutendexId(gutendexId).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Long getTotalBooks() {
        return bookRepository.count();
    }

    public Long getTotalAuthors() {
        return authorRepository.count();
    }
}
