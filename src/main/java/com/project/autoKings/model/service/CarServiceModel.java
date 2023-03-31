package com.project.autoKings.model.service;

import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.enums.Transmission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarServiceModel {

    private Long id;
    private String make;

    private String model;

    private String imageUrl;

    private Long mileage;

    private Engine engine;

    private Transmission transmission;

    private Integer released;

    private String VIN;

    private List<Services> services;

    private String problemDescription;

    private LocalDate repairmentDays;

    public CarServiceModel(){}

    public CarServiceModel(Long id,String make, String model, String imageUrl, Long mileage, Engine engine,
                           Transmission transmission, Integer released, String VIN, String problemDescription) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = VIN;
        this.services = new ArrayList<>();
        this.problemDescription = problemDescription;
    }

    public CarServiceModel(Long id,String make, String model, String imageUrl, Long mileage, Engine engine,
                           Transmission transmission, Integer released, String VIN, String problemDescription,
                           List<Services> services,LocalDate repairmentDays) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = VIN;
        this.problemDescription = problemDescription;
        this.services = services;
        this.repairmentDays = repairmentDays;
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

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> problem) {
        this.services = problem;
    }

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

    public void addServices(Services services){
        this.services.add(services);
    }

    public LocalDate getRepairmentDays() {
        return repairmentDays;
    }

    public void setRepairmentDays(LocalDate repairmentDays) {
        this.repairmentDays = repairmentDays;
    }
}
