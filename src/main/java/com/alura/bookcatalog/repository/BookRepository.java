package com.alura.bookcatalog.repository;

import com.alura.bookcatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByGutendexId(Long gutendexId);
    
    boolean existsByGutendexId(Long gutendexId);
}
