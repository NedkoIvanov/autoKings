package com.project.autoKings.model.service;

import com.project.autoKings.model.enums.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public class MechanicServiceModel {

    private Long id;

    private String fullName;

    private Integer years;

    private String imageUrl;

    private String phoneNumber;

    private LocalDate workedSince;

    private Integer chosen;

    private List<Services> specialization;


    public MechanicServiceModel(){}

    public MechanicServiceModel(Long id, String fullName, Integer years,
                                String imageUrl, String phoneNumber, LocalDate workedSince,Integer chosen,List<Services> specialization) {
        this.id = id;
        this.fullName = fullName;
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.workedSince = workedSince;
        this.chosen = chosen;
        this.specialization = specialization;

    }

    public MechanicServiceModel(Long id, String fullName, Integer years,
                                String imageUrl, String phoneNumber, LocalDate workedSince,Integer chosen) {
        this.id = id;
        this.fullName = fullName;
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.workedSince = workedSince;
        this.chosen = chosen;


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

    public LocalDate getWorkedSince() {
        return workedSince;
    }

    public void setWorkedSince(LocalDate workedSince) {
        this.workedSince = workedSince;
    }

    public Integer getChosen() {
        return chosen;
    }

    public void setChosen(Integer chosen) {
        this.chosen = chosen;
    }

    public List<Services> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Services> specialization) {
        this.specialization = specialization;
    }
}
