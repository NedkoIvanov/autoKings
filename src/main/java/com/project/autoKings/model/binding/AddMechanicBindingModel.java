package com.project.autoKings.model.binding;


import com.project.autoKings.model.enums.Services;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
public class AddMechanicBindingModel {

    @Size(min=6)
    private String fullName;

    @Min(value = 18)
    private Integer years;
    //todo image url validation
    private String imageUrl;
    //todo phone number validation +359882712304
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate workedSince;
    private List<Services> specialization;

    public AddMechanicBindingModel(){

    }
    public AddMechanicBindingModel(String fullName, Integer years,
                                   String imageUrl, String phoneNumber, LocalDate workedSince, List<Services> specialization) {

        this.fullName = fullName;
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.workedSince = workedSince;
        this.specialization = specialization;
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

    public List<Services> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Services> specialization) {
        this.specialization = specialization;
    }
}
