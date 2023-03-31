package com.project.autoKings.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class SetDateBindingModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate repairmentDays;

    public SetDateBindingModel(){}
    public SetDateBindingModel(LocalDate repairmentDays) {
        this.repairmentDays = repairmentDays;
    }

    public LocalDate getRepairmentDays() {
        return repairmentDays;
    }

    public void setRepairmentDays(LocalDate repairmentDays) {
        this.repairmentDays = repairmentDays;
    }
}
