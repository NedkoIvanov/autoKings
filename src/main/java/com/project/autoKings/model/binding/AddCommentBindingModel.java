package com.project.autoKings.model.binding;


import javax.validation.constraints.Size;

public class AddCommentBindingModel {
    @Size(min=8)
    private String textContent;

    public AddCommentBindingModel(){}

    public AddCommentBindingModel(String textContent) {
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
