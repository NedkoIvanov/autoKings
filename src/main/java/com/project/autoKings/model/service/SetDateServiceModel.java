package com.project.autoKings.model.service;

import java.time.LocalDate;

public class SetDateServiceModel {

    private Long id;

    private LocalDate repairmentDate;

    public SetDateServiceModel(){

    }

    public SetDateServiceModel(Long id, LocalDate repairmentDate) {
        this.id = id;
        this.repairmentDate = repairmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRepairmentDate() {
        return repairmentDate;
    }

    public void setRepairmentDate(LocalDate repairmentDate) {
        this.repairmentDate = repairmentDate;
    }
}
