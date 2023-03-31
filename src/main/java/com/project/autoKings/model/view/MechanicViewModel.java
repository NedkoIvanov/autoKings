package com.project.autoKings.model.view;

import java.time.LocalDate;

public class MechanicViewModel {

    private Long id;
    private String imageUrl;
    private String fullName;
    private LocalDate workedSince;
    private String phoneNumber;

    private Integer chosen;

    public MechanicViewModel(){}

    public MechanicViewModel(Long id, String imageUrl, String fullName, LocalDate workedSince, String phoneNumber, Integer chosen) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.fullName = fullName;
        this.workedSince = workedSince;
        this.phoneNumber = phoneNumber;
        this.chosen = chosen;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getWorkedSince() {
        return workedSince;
    }

    public void setWorkedSince(LocalDate workedSince) {
        this.workedSince = workedSince;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getChosen() {
        return chosen;
    }

    public void setChosen(Integer chosen) {
        this.chosen = chosen;
    }
}
