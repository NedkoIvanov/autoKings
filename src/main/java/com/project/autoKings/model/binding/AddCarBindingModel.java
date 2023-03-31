package com.project.autoKings.model.binding;


import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.enums.Transmission;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class AddCarBindingModel {

    @Size(min=3)
    private String make;

    @Size(min=2)
    private String model;

    //ToDo implement image url validation
    private String imageUrl;

    @Min(value = 0)
    private Long mileage;

    private Engine engine;

    private Transmission transmission;

    private Integer released;

    private String VIN;

    private List<Services> services;

    @Length(min=10)
    private String problemDescription;

    public AddCarBindingModel(){}

    public AddCarBindingModel(String make, String model, String imageUrl, Long mileage, Engine engine, Transmission transmission,
                              Integer released, String VIN,String problemDescription) {
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

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}
