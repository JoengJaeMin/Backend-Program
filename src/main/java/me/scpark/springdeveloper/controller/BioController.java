package me.scpark.springdeveloper.controller;

import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.ArticleResponse;
import me.scpark.springdeveloper.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BioController {

    @Autowired
    private BlogService blogService;

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
