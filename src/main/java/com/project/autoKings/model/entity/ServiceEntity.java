package com.project.autoKings.model.entity;

import com.project.autoKings.model.enums.Services;

import javax.persistence.*;

@Entity
public class ServiceEntity extends Base{
    @Lob
    private String imageUrl;

    private String title;

    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private Services services;

    public ServiceEntity(){
    }

    public ServiceEntity(String imageUrl, String title, String description, Services services) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.services = services;
    }

    public ServiceEntity(Services services){
        this.services = services;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
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
