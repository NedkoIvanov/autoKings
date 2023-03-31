package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Services;

public class ServiceTypesViewModel {

    private Long id;

    private String imageUrl;

    private String title;

    private Services services;

    public ServiceTypesViewModel(){}

    public ServiceTypesViewModel(Long id, String imageUrl, String title, Services services) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
