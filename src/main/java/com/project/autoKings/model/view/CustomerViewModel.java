package com.project.autoKings.model.view;

public class CustomerViewModel {

    private Long id;

    private String username;
    private String fullName;

    private String email;

    private String imageUrl;
    public CustomerViewModel(){}

    public CustomerViewModel(Long id, String username,String fullName, String email, String imageUrl) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public CustomerViewModel(String fullName, String email, String imageUrl) {
        this.fullName = fullName;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
