package com.project.autoKings.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity extends Base {


    private String fullName;

    private String username;

    private String email;

    private String imageUrl;

    private String password;

    private Integer age;

    private LocalDate registeredOn;

    private String phoneNumber;

    @ManyToMany
    private List<RoleEntity> roles;
    @OneToMany(mappedBy = "owner",targetEntity = CarEntity.class)
    private List<CarEntity> cars;
    public UserEntity(){}

    public UserEntity(String fullName, String username, String email, String imageUrl,
                      String password, Integer age, LocalDate registeredOn, String phoneNumber, List<RoleEntity> roles) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
        this.age = age;
        this.registeredOn = registeredOn;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.cars = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }

    public void addCar(CarEntity car){
        this.cars.add(car);
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
