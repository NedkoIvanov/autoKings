package com.project.autoKings.model.binding;

import com.project.autoKings.model.enums.Services;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class AddNewProblemsBindingModel {

    private List<Services> services;

    @Length(min=10)
    private String problemDescription;

    public AddNewProblemsBindingModel(){}

    public AddNewProblemsBindingModel(List<Services> services, String problemDescription) {
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

