package com.project.autoKings.model.service;

import com.project.autoKings.model.enums.Services;

import java.util.List;

public class AddNewProblemServiceModel {

    private List<Services> services;
    private String problemDescription;

    public AddNewProblemServiceModel(){

    }

    public AddNewProblemServiceModel(List<Services> services, String problemDescription) {
        this.services = services;
        this.problemDescription = problemDescription;
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
