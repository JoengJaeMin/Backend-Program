package me.JJM.springbootdeveloper.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import me.JJM.springbootdeveloper.service.BlogService;
import me.scpark.springdeveloper.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<Article> articles = blogService.findAll();
        return "articleList";
    }
}
