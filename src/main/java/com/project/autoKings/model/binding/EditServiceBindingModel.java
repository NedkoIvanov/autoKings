package com.project.autoKings.model.binding;

import com.project.autoKings.model.enums.Services;

import javax.validation.constraints.Size;

public class EditServiceBindingModel {

    //todo image validation
    private String imageUrl;

    private String title;

    @Size(min=50)
    private String description;


    public EditServiceBindingModel(){}


    public EditServiceBindingModel(String imageUrl, String title, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
