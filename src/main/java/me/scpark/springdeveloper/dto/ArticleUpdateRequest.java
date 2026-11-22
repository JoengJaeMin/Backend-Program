
package me.scpark.springdeveloper.dto;

public class ArticleUpdateRequest {

    private String title;
    private String content;

    public ArticleUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
