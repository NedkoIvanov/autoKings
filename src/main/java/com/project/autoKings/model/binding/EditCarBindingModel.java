package com.project.autoKings.model.binding;

import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Transmission;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class EditCarBindingModel {
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

    public EditCarBindingModel(){}

    public EditCarBindingModel(String make, String model, String imageUrl,
                               Long mileage, Engine engine, Transmission transmission, Integer released, String VIN) {
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = VIN;
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

}
