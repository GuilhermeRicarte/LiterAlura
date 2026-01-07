package com.alura.bookcatalog.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private Long id;

    private String title;

    private List<AuthorDTO> authors;

    private List<String> languages;

    @JsonAlias("download_count")
    private Integer downloadCount;

    @JsonAlias("cover_image")
    private String coverImageUrl;

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
