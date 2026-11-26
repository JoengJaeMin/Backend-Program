package me.scpark.springdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.dto.ArticleUpdateRequest;
import me.scpark.springdeveloper.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    BlogRepository blogRepository;

    // 각 테스트가 서로 영향을 주지 않도록 매번 DB를 비운다.
    @BeforeEach
    void setUp() {
        blogRepository.deleteAll();
    }

    // 테스트 1. 글 생성
    @Test
    @DisplayName("addArticle: 글 생성에 성공한다.")
    void addArticle() throws Exception {

        // Given: 요청 DTO 생성 및 JSON 변환
        AddArticleRequest request = new AddArticleRequest(
                "테스트 제목",
                "테스트 내용"
        );
        String requestBody = objectMapper.writeValueAsString(request);

        // When: POST /api/articles 요청
        ResultActions result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // Then: 201 Created 및 DB 저장 결과 확인
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles).hasSize(1);
        assertThat(articles.get(0).getTitle()).isEqualTo("테스트 제목");
        assertThat(articles.get(0).getContent()).isEqualTo("테스트 내용");
    }

    // 테스트 3. 글 수정
    @Test
    @DisplayName("updateArticle: 글 수정에 성공한다.")
    void updateArticle() throws Exception {

        // Given: 글 1개를 저장하고, 수정 요청 객체 생성
        Article savedArticle = blogRepository.save(
                new Article("기존 제목", "기존 내용")
        );
        ArticleUpdateRequest request = new ArticleUpdateRequest(
                "새로운 제목",
                "새로운 내용"
        );

        // When: PUT /api/articles/{id} 요청 (id는 저장한 객체에서 가져옴)
        ResultActions result = mockMvc.perform(put("/api/articles/{id}", savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // Then: 200 OK 및 DB에 수정된 내용 반영 확인
        result.andExpect(status().isOk());

        Article updatedArticle = blogRepository.findById(savedArticle.getId())
                .orElseThrow();

        assertThat(updatedArticle.getTitle()).isEqualTo("새로운 제목");
        assertThat(updatedArticle.getContent()).isEqualTo("새로운 내용");
    }

    // 테스트 5. 글 목록 조회
    @Test
    @DisplayName("findAllArticles: 글 목록 조회에 성공한다.")
    void findAllArticles() throws Exception {

        // Given: 글 2건을 미리 저장
        blogRepository.save(new Article("제목1", "내용1"));
        blogRepository.save(new Article("제목2", "내용2"));

        // When: GET /api/articles 요청
        ResultActions result = mockMvc.perform(get("/api/articles")
                .accept(MediaType.APPLICATION_JSON));

        // Then: 200 OK, 배열 길이, 각 글의 title/content 확인
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("제목1"))
                .andExpect(jsonPath("$[0].content").value("내용1"))
                .andExpect(jsonPath("$[1].title").value("제목2"))
                .andExpect(jsonPath("$[1].content").value("내용2"));
    }
}