package com.alura.bookcatalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponseDTO {

    private Integer count;

    private String next;

    private String previous;

    private List<BookDTO> results;

    @Override
    public String toString() {
        return "GutendexResponseDTO{" +
                "count=" + count +
                ", results size=" + (results != null ? results.size() : 0) +
                '}';
    }
}
