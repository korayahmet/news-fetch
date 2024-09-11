package com.example.newsfetch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @JsonProperty("source")
    private Source source;

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("url")
    private String url;

    @JsonProperty("urlToImage")
    private String urlToImage;

    @JsonProperty("publishedAt")
    private String publishedAt;

    @JsonProperty("content")
    private String content;
}
