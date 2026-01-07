package com.alura.bookcatalog.service;

import com.alura.bookcatalog.dto.GutendexResponseDTO;
import com.alura.bookcatalog.dto.BookDTO;
import com.alura.bookcatalog.dto.AuthorDTO;
import com.alura.bookcatalog.model.Book;
import com.alura.bookcatalog.model.Author;
import com.alura.bookcatalog.repository.BookRepository;
import com.alura.bookcatalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private static final Logger logger = LoggerFactory.getLogger(GutendexService.class);
    private static final String GUTENDEX_API_URL = "https://gutendex.com/books";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public GutendexResponseDTO fetchBooksFromApi(Integer page) {
        String url = GUTENDEX_API_URL + "?page=" + page;
        logger.info("Fetching books from Gutendex API: {}", url);
        
        try {
            GutendexResponseDTO response = restTemplate.getForObject(url, GutendexResponseDTO.class);
            logger.info("Successfully fetched {} books from API", response.getResults().size());
            return response;
        } catch (Exception e) {
            logger.error("Error fetching books from API: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch books from Gutendex API", e);
        }
    }

    public void saveBooks(List<BookDTO> bookDtos) {
        for (BookDTO bookDto : bookDtos) {
            try {
                // Check if book already exists
                if (bookRepository.existsByGutendexId(bookDto.getId())) {
                    logger.debug("Book with Gutendex ID {} already exists, skipping", bookDto.getId());
                    continue;
                }

                Book book = new Book();
                book.setGutendexId(bookDto.getId());
                book.setTitle(bookDto.getTitle());
                book.setLanguages(bookDto.getLanguages());
                book.setDownloadCount(bookDto.getDownloadCount());
                book.setCoverImageUrl(bookDto.getCoverImageUrl());

                // Handle authors
                if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
                    AuthorDTO authorDto = bookDto.getAuthors().get(0);
                    Author author = authorRepository.findByName(authorDto.getName());
                    
                    if (author == null) {
                        author = new Author();
                        author.setName(authorDto.getName());
                        author.setBirthYear(authorDto.getBirthYear());
                        author.setDeathYear(authorDto.getDeathYear());
                        author = authorRepository.save(author);
                    }
                    
                    book.setAuthor(author);
                }

                bookRepository.save(book);
                logger.debug("Book saved: {}", book.getTitle());

            } catch (Exception e) {
                logger.error("Error saving book {}: {}", bookDto.getTitle(), e.getMessage());
            }
        }
    }

    public void importBooksFromApiPage(Integer page) {
        GutendexResponseDTO response = fetchBooksFromApi(page);
        if (response != null && response.getResults() != null) {
            saveBooks(response.getResults());
        }
    }
}
