package com.project.autoKings.model.binding;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RegisterUserBindingModel {

    @Size(min=8,max=30)
    private String fullName;

    @Size(min=5,max=20)
    private String username;

    @Email
    private String email;

    //toDo implement custom validation for phoneNumber
    private String phoneNumber;

    private String imageUrl;


    //Todo implement custom annotation for password validation
    @Size(min=5,max=20)
    private String password;
    private String confirmPassword;

    @Min(value = 18)
    private Integer age;

    public RegisterUserBindingModel(){

    }

    public RegisterUserBindingModel(String username, String fullName, String email, String phoneNumber, String imageUrl,
                                    Integer age, String password, String confirmPassword) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.age = age;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
