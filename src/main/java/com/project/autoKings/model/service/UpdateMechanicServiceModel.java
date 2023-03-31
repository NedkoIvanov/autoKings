package com.project.autoKings.model.service;

import com.project.autoKings.model.enums.Services;
import java.util.List;

public class UpdateMechanicServiceModel {

    private Long id;
    private Integer years;
    private String imageUrl;
    private String phoneNumber;
    private List<Services> specialization;

    public UpdateMechanicServiceModel(){}


    public UpdateMechanicServiceModel(Long id, Integer years,
                                      String imageUrl, String phoneNumber, List<Services> specialization) {
        this.id = id;
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
