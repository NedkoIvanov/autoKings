package com.project.autoKings.service;

import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.EditServiceServiceModel;
import com.project.autoKings.model.service.ServiceServiceModel;
import com.project.autoKings.model.view.ServiceTypesViewModel;
import com.project.autoKings.model.view.ServiceViewModel;

import java.util.List;
public interface ServicesService {

    void checkAndSeed();

    List<ServiceServiceModel> findAllServices();

    ServiceViewModel findById(Long id);

    void editService(Long id,EditServiceServiceModel editServiceServiceModel);

    ServiceEntity findEntityById(Long id);

    List<ServiceEntity> findBySpecialization(List<Services> services);

    List<ServiceTypesViewModel> findByCarProblems(List<Services> problems);

}
