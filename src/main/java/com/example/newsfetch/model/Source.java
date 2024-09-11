package com.example.newsfetch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Source {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}