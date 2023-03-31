package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Services;

import java.time.LocalDate;
import java.util.List;

public class UpdateMechanicViewModel {

    private Long id;
    private String imageUrl;
    private String fullName;
    private LocalDate workedSince;
    private String phoneNumber;

    private List<Services> specialization;

    public UpdateMechanicViewModel(){}

    public UpdateMechanicViewModel(Long id, String imageUrl, String fullName, LocalDate workedSince, String phoneNumber, List<Services> specialization) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.fullName = fullName;
        this.workedSince = workedSince;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
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

    public List<Services> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Services> specialization) {
        this.specialization = specialization;
    }
}
