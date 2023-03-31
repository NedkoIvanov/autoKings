package com.project.autoKings.model.service;

public class MechanicInfoForCommentsServiceModel {

    private Long id;
    private String fullName;

    private String imageUrl;

    public MechanicInfoForCommentsServiceModel(){}

    public MechanicInfoForCommentsServiceModel(Long id, String fullName, String imageUrl) {
        this.id = id;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
