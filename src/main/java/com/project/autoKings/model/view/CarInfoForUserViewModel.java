package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Services;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;

public class CarInfoForUserViewModel {

    private Long id;

    private String make;

    private String model;

    private String imageUrl;

    private LinkedHashSet<String> mechanicFullName;

    private Boolean accepted;
    private LocalDate repairmentDays;

    private List<Services> services;

    private String ownerName;

    public CarInfoForUserViewModel(){}

    public CarInfoForUserViewModel(Long id, String make, String model, String imageUrl,
                                   LinkedHashSet<String> mechanicFullName, Boolean accepted,
                                   LocalDate repairmentDays,List<Services> services,String ownerName) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mechanicFullName = mechanicFullName;
        this.accepted = accepted;
        this.repairmentDays = repairmentDays;
        this.services = services;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LinkedHashSet<String> getMechanicFullName() {
        return mechanicFullName;
    }

    public void setMechanicFullName(LinkedHashSet<String> mechanicFullName) {
        this.mechanicFullName = mechanicFullName;
    }

    public LocalDate getRepairmentDays() {
        return repairmentDays;
    }

    public void setRepairmentDays(LocalDate repairmentDays) {
        this.repairmentDays = repairmentDays;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
