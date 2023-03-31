package com.project.autoKings.model.view;


import java.time.LocalDateTime;

public class CommentViewModel {

    private Long id;

    private String textContent;

    private LocalDateTime created;

    private String authorName;

    private String authorImage;

    public CommentViewModel(){}
    public CommentViewModel(Long id, String textContent, LocalDateTime created, String authorName, String authorImage) {
        this.id = id;
        this.textContent = textContent;
        this.created = created;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }
}
