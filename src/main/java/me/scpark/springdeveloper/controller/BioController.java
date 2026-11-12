package me.scpark.springdeveloper.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class BioController {
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        Article article = blogService.getbyId(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
}
