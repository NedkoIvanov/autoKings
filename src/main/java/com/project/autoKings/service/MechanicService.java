package com.project.autoKings.service;

import com.project.autoKings.model.binding.AddMechanicBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.CarServiceModel;
import com.project.autoKings.model.service.MechanicInfoForCommentsServiceModel;
import com.project.autoKings.model.service.MechanicServiceModel;
import com.project.autoKings.model.service.UpdateMechanicServiceModel;
import com.project.autoKings.model.view.UpdateMechanicViewModel;

import java.util.List;

public interface MechanicService {

    void saveToDb(MechanicServiceModel mechanicServiceModel,List<Services> specializations);

    List<MechanicServiceModel> findAllMechanics();

    List<MechanicServiceModel> findAllMechanicsBySpecs(String specialization);

    void deleteMechanic(Long id);

    UpdateMechanicViewModel findById(Long id);

    void updateMechanic(Long id,UpdateMechanicServiceModel updatedMechanic,List<Services> services);

    MechanicEntity findEntityById(Long id);

    void chooseForServicing(Long id, CarServiceModel newestCar);

    boolean checkIfAllAreActive(List<MechanicServiceModel> mechanicServiceModel);

    boolean isResponsible(CarServiceModel newestCar, List<MechanicServiceModel> mechanicServiceModel);

    MechanicInfoForCommentsServiceModel findCurrentMechanic(Long id);

    boolean checkMechanicFields(AddMechanicBindingModel addMechanicBindingModel);
}
