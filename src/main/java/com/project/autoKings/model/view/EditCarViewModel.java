package com.project.autoKings.model.view;

import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.enums.Transmission;


import java.util.List;

public class EditCarViewModel {

    private Long id;

    private String make;

    private String model;

    private String imageUrl;

    private Long mileage;

    private Engine engine;

    private Transmission transmission;

    private Integer released;

    private String VIN;

    public EditCarViewModel(){}

    public EditCarViewModel(Long id, String make, String model, String imageUrl, Long mileage,
                            Engine engine, Transmission transmission, Integer released, String VIN) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = VIN;
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
