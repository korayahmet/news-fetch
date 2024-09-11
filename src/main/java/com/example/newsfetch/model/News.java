package com.example.newsfetch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @JsonProperty("status")
    private String status;

    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("articles")
    private List<Article> articles;
}
