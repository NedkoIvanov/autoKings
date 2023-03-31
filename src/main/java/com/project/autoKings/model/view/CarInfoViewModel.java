package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Transmission;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class CarInfoViewModel {

    private Long id;
    private String make;

    private String model;

    private String imageUrl;

    private Long mileage;

    private Engine engine;

    private Transmission transmission;

    private Integer released;


    private String problemDescription;

    private String VIN;

    private String fullName;

    private String phoneNumber;

    private LocalDate repairmentDays;

    private LinkedHashSet<String> mechanicFullName;

    private Boolean accepted;

    public CarInfoViewModel(){}

    public CarInfoViewModel(Long id, String make, String model, String imageUrl, Long mileage, Engine engine,
                            Transmission transmission, Integer released,
                            String problemDescription, String vin, String fullName,
                            String phoneNumber, LocalDate repairmentDays,
                            LinkedHashSet<String> mechanicFullName, Boolean accepted) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = vin;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.repairmentDays = repairmentDays;
        this.problemDescription = problemDescription;
        this.mechanicFullName = mechanicFullName;
        this.accepted = accepted;
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

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

//    public List<Services> getServices() {
//        return services;
//    }
//
//    public void setServices(List<Services> services) {
//        this.services = services;
//    }
//
    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRepairmentDays() {
        return repairmentDays;
    }

    public void setRepairmentDays(LocalDate repairmentDays) {
        this.repairmentDays = repairmentDays;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public LinkedHashSet<String> getMechanicFullName() {
        return mechanicFullName;
    }

    public void setMechanicFullName(LinkedHashSet<String> mechanicFullName) {
        this.mechanicFullName = mechanicFullName;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
