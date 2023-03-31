package com.project.autoKings.model.entity;

import com.project.autoKings.model.enums.Services;

import javax.persistence.*;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

@Entity
public class MechanicEntity extends Base{

    private String fullName;

    private Integer years;

    @Lob
    private String imageUrl;

    private String phoneNumber;

    private LocalDate workedSince;

    private LocalDate added;

    private Integer chosen;

    @ManyToMany
    private List<ServiceEntity> specialization;


    @OneToMany(mappedBy="mechanic")
    private List<CommentEntity> comments;

    public MechanicEntity(){}

    public MechanicEntity(String fullName, Integer years, String imageUrl,
                          String phoneNumber, LocalDate workedSince, LocalDate added, Integer chosen) {
        this.fullName = fullName;
        this.years = years;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.workedSince = workedSince;
        this.added = added;
        this.chosen = chosen;
        this.specialization = new ArrayList<>();
        this.comments = new ArrayList<>();
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

    public List<ServiceEntity> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<ServiceEntity> specialization) {
        this.specialization = specialization;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public Integer getChosen() {
        return chosen;
    }

    public void setChosen(Integer chosen) {
        this.chosen = chosen;
    }

    public List<Services> getTypesOfSpecialization(){
        List<Services> services = new ArrayList<>();
        if(this.specialization.size()==0){
            return null;
        }else{
            for(int i=0;i<this.specialization.size();i++){
                services.add(this.specialization.get(i).getServices());
            }
            return services;
        }


    }
}
