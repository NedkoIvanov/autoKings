package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Services;

public class ServiceViewModel {

    private Long id;
    private String imageUrl;

    private String title;

    private String description;
    private Services services;

    public ServiceViewModel(){}

    public ServiceViewModel(Long id, String imageUrl, String title, String description, Services services) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
