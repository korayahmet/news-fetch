package com.example.newsfetch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.newsfetch.model.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsFetchService {

    //debug
    private static final Logger logger = LoggerFactory.getLogger(NewsFetchService.class);

    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines";
    private static final String API_KEY = "";           //API KEY HERE

    private final RestTemplate restTemplate;
    private final AmqpTemplate amqpTemplate;

    // RabbitMQ Properties
    private final String exchange = "exchange.news";
    private final String routingkey = "routing.news";
    // private final String queue = "news-queue";


    public News fetchNews() {
    String country = "us"; // Set the country code according to your needs
    String pageSize = "10"; // Limit the number of news articles
    String url = NEWS_API_URL + "?apiKey=" + API_KEY + "&country=" + country + "&pageSize=" + pageSize;
    News news = restTemplate.getForObject(url, News.class);

    if (news != null) {
        logger.info("Fetched {} news articles.", news.getTotalResults());

        // Convert to JSON and send it to RabbitMQ
        try {
            String newsJson = new ObjectMapper().writeValueAsString(news);
            amqpTemplate.convertAndSend(exchange, routingkey, newsJson); // Send news as JSON string to RabbitMQ
            // amqpTemplate.convertAndSend(exchange, queue, newsJson);  // Send news to the queue
        } catch (JsonProcessingException e) {
            // Handle the exception as needed, log or rethrow
            logger.error("Error converting News to JSON: {}", e.getMessage(), e);
        }

        return news;
    } else {
        logger.warn("Fetched news is null.");
        return null;
    }
}

    @Scheduled(fixedRate = 300000) // 300,000 milliseconds = 5 minutes
    public void scheduleNewsFetch() {
        logger.info("Fetching news...");    //debug line
        News news = fetchNews();
        logger.info("Fetched {} news articles.", news.getTotalResults());      //debug line
    }
}
