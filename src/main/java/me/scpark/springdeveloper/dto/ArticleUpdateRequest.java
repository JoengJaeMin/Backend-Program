package me.scpark.springdeveloper.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleUpdateRequest {
    private String title;
    private String content;
}
