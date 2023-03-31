package com.project.autoKings.model.entity;

import com.project.autoKings.model.enums.Engine;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.enums.Transmission;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CarEntity extends Base{

    private String make;

    private String model;

    @Lob
    private String imageUrl;


    private Long mileage;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Integer released;

    private String VIN;

    @ManyToMany
    private List<ServiceEntity> problem;

    private String problemDescription;

    private LocalDate added;

    private LocalDate repairmentDays;

    private Boolean accepted;

    //maybe i need to add cascade type all
    @ManyToOne
    private UserEntity owner;

    @ManyToMany
    private List<MechanicEntity> mechanics;


    public CarEntity(){}

    public CarEntity(String make, String model, String imageUrl, Long mileage, Engine engine,
                     Transmission transmission, Integer released, String VIN,
                     String problemDescription, LocalDate added, LocalDate repairmentDays,
                     Boolean accepted, UserEntity owner, List<MechanicEntity> mechanics) {
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.engine = engine;
        this.transmission = transmission;
        this.released = released;
        this.VIN = VIN;
        this.added = added;
        this.repairmentDays = repairmentDays;
        this.accepted = accepted;
        this.mechanics = mechanics;
        this.problem = new ArrayList<>();
        this.problemDescription = problemDescription;
        this.owner = owner;
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

    public List<ServiceEntity> getProblem() {
        return problem;
    }

    public void setProblem(List<ServiceEntity> problem) {
        this.problem = problem;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<Services> getServices(){
        List<Services> services = new ArrayList<>();
        if(this.problem.size()==0){
            return null;
        }else{
            for(int i=0;i<this.problem.size();i++){
                services.add(this.problem.get(i).getServices());
            }
            return services;
        }


    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
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

    public List<MechanicEntity> getMechanics() {
        return mechanics;
    }

    public void setMechanics(List<MechanicEntity> mechanics) {
        this.mechanics = mechanics;
    }

    public List<String> mechanicNames(){
        List<String> names = new ArrayList<>();
        if(this.mechanics.isEmpty()){
            return null;
        }else{
            for(int i=0;i<this.mechanics.size();i++){
                names.add(this.mechanics.get(i).getFullName());
            }
            return names;
        }
    }
}
