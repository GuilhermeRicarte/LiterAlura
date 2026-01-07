package com.alura.bookcatalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long gutendexId;

    @Column(nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;

    private Integer downloadCount;

    @Column(columnDefinition = "TEXT")
    private String coverImageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", gutendexId=" + gutendexId +
                ", title='" + title + '\'' +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                ", author=" + author +
                '}';
    }
}
