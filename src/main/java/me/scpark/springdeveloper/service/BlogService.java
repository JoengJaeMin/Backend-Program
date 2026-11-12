package me.scpark.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public Article getById(Long id) {
        return findById(id);
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }


}
