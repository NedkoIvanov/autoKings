package com.project.autoKings.service;

import com.project.autoKings.model.binding.AddCarBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.entity.UserEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.*;
import com.project.autoKings.model.view.EditCarViewModel;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    CarEntity saveCarToDb(CarServiceModel carServiceModel, String username, List<Services> services);

    List<CarInfoForUserServiceModel> findOwnedCarsByUsername(String username);

    List<CarEntity> findCarsEntityByOwner(UserEntity owner);

    void deleteCar(Long id);

    EditCarViewModel findCarViewToEditById(Long id);

    void editCar(Long id, EditCarServiceModel editCarServiceModel);

    CarEntity findCarEntityById(Long id);

    List<CarInfoServiceModel> findAllCars();

    CarServiceModel findNewestCar(String ownerUserName);

    void setRepairmentDate(SetDateServiceModel setDateServiceModel,Long id);

    void removeCurrentRepairmentDate(Long id);

    void removeProblems(CarServiceModel carServiceModel, MechanicEntity mechanic);

    void addNewProblems(Long id, AddNewProblemServiceModel addNewProblemServiceModel,List<Services> services);

    boolean checkCarFields(AddCarBindingModel addCarBindingModel);

    public void carIsRepairedScheduler();


}
