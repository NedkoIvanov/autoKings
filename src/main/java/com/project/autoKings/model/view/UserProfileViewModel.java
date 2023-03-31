package com.project.autoKings.model.view;

public class UserProfileViewModel {

    private Long id;
    private String imageUrl;

    private String fullName;

    private String username;

    private String email;

    public UserProfileViewModel(){

    }

    public UserProfileViewModel(Long id, String imageUrl, String fullName, String username, String email) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
