package com.project.autoKings.model.service;

public class CommentServiceModel {

    private Long mechanicId;

    private String textContent;

    private String authorName;

    public CommentServiceModel(){}

    public CommentServiceModel(Long mechanicId, String textContent, String authorName) {
        this.mechanicId = mechanicId;
        this.textContent = textContent;
        this.authorName = authorName;
    }

    public Long getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(Long mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
