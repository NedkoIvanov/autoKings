package com.project.autoKings.model.binding;

import com.project.autoKings.model.enums.Services;

import javax.validation.constraints.Min;
import java.util.List;

public class MechanicUpdateBindingModel {

    @Min(value=18)
    private Integer years;
    private String imageUrl;
    private String phoneNumber;
    private List<Services> specialization;

    public MechanicUpdateBindingModel(){}

    public MechanicUpdateBindingModel(Integer years, String imageUrl, String phoneNumber, List<Services> specialization) {
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
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
