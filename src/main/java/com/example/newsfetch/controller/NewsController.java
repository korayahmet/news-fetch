package com.example.newsfetch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsfetch.model.News;
import com.example.newsfetch.service.NewsFetchService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final NewsFetchService newsFetchService;

    @GetMapping("/latest")
    public ResponseEntity<News> getLatestNews() {
        // Call the newsFetchService to get the latest news
        News news = newsFetchService.fetchNews();

        if (news != null) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
