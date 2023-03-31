package com.project.autoKings.service.implementation;

import com.project.autoKings.model.binding.AddMechanicBindingModel;
import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.CarServiceModel;
import com.project.autoKings.model.service.MechanicInfoForCommentsServiceModel;
import com.project.autoKings.model.service.MechanicServiceModel;
import com.project.autoKings.model.service.UpdateMechanicServiceModel;
import com.project.autoKings.model.view.UpdateMechanicViewModel;
import com.project.autoKings.repository.MechanicRepository;
import com.project.autoKings.service.CarService;
import com.project.autoKings.service.MechanicService;
import com.project.autoKings.service.ServicesService;
import com.project.autoKings.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final ModelMapper modelMapper;

    private final ServicesService servicesService;

    private final CarService carService;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper modelMapper,
                               ServicesService servicesService, CarService carService) {
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.servicesService = servicesService;
        this.carService = carService;
    }

    @Override
    @Transactional
    public void saveToDb(MechanicServiceModel mechanicServiceModel, List<Services> specializations) {
        MechanicEntity mechanic = new MechanicEntity();
        mechanic.setFullName(mechanicServiceModel.getFullName());
        mechanic.setImageUrl(mechanicServiceModel.getImageUrl());
        mechanic.setPhoneNumber(mechanicServiceModel.getPhoneNumber());
        mechanic.setWorkedSince(mechanicServiceModel.getWorkedSince());
        mechanic.setYears(mechanicServiceModel.getYears());
        mechanic.setAdded(LocalDate.now());
        List<ServiceEntity> specialization = this.servicesService.findBySpecialization(specializations);
        mechanic.setSpecialization(specialization);
        mechanic.setChosen(0);
        this.mechanicRepository.save(mechanic);
    }

    @Override
    public List<MechanicServiceModel> findAllMechanics() {

        List<MechanicEntity> mechanics = this.mechanicRepository.findAll();
        List<MechanicServiceModel> mechanicServiceModel = new ArrayList<>();

        if (mechanics.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < mechanics.size(); i++) {
                mechanicServiceModel.add(
                        new MechanicServiceModel(
                                mechanics.get(i).getId(),
                                mechanics.get(i).getFullName(),
                                mechanics.get(i).getYears(),
                                mechanics.get(i).getImageUrl(),
                                mechanics.get(i).getPhoneNumber(),
                                mechanics.get(i).getWorkedSince(),
                                mechanics.get(i).getChosen()
                        )
                );
            }
            return mechanicServiceModel;
        }
    }

    @Override
    @Transactional
    public List<MechanicServiceModel> findAllMechanicsBySpecs(String specialization) {
        List<MechanicEntity> mechanics = this.mechanicRepository.findAllBySpecialization(Services.valueOf(specialization));
        List<MechanicServiceModel> mechanicServiceModel = new ArrayList<>();
        if (mechanics == null && mechanics.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < mechanics.size(); i++) {
                mechanicServiceModel.add(
                        new MechanicServiceModel(
                                mechanics.get(i).getId(),
                                mechanics.get(i).getFullName(),
                                mechanics.get(i).getYears(),
                                mechanics.get(i).getImageUrl(),
                                mechanics.get(i).getPhoneNumber(),
                                mechanics.get(i).getWorkedSince(),
                                mechanics.get(i).getChosen(),
                                mechanics.get(i).getTypesOfSpecialization()
                        )
                );
            }
            return mechanicServiceModel;
        }
    }

    @Override
    public void deleteMechanic(Long id) {
        if (!this.mechanicRepository.findById(id).isEmpty()) {
            this.mechanicRepository.deleteById(id);
        }
    }

    @Override
    public UpdateMechanicViewModel findById(Long id) {
        MechanicEntity mechanic = findEntityById(id);
        return this.modelMapper.map(mechanic, UpdateMechanicViewModel.class);
    }

    @Override
    public void updateMechanic(Long id, UpdateMechanicServiceModel updatedMechanic, List<Services> services) {
        MechanicEntity mechanic = findEntityById(id);
        mechanic.setImageUrl(updatedMechanic.getImageUrl());
        mechanic.setPhoneNumber(updatedMechanic.getPhoneNumber());
        mechanic.setYears(updatedMechanic.getYears());
        List<ServiceEntity> serviceEntities = this.servicesService.findBySpecialization(services);
        mechanic.setSpecialization(serviceEntities);
        this.mechanicRepository.save(mechanic);
    }

    @Override
    public MechanicEntity findEntityById(Long id) {
        return this.mechanicRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Mechanic with requested id:" + id + " not found!")
        );
    }

    @Override
    public void chooseForServicing(Long mechanicId, CarServiceModel car) {
        MechanicEntity mechanic = findEntityById(mechanicId);
        if (mechanic.getChosen() == null) {
            mechanic.setChosen(0);
        } else {
            mechanic.setChosen(mechanic.getChosen().intValue() + 1);
            this.carService.removeProblems(car, mechanic);
        }

        this.mechanicRepository.save(mechanic);
    }

    @Override
    public boolean checkIfAllAreActive(List<MechanicServiceModel> mechanicServiceModel) {
        int mechanicsCount = mechanicServiceModel.size();
        int count = 0;
        for (MechanicServiceModel mechanics : mechanicServiceModel) {
            if (mechanics.getChosen() == 5) {
                count++;
            }
        }
        if (mechanicsCount == count) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean isResponsible(CarServiceModel newestCar, List<MechanicServiceModel> mechanicServiceModel) {
        List<Services> carProblems = newestCar.getServices();
        List<Services> mechanicSpecs = new ArrayList<>();
        for (MechanicServiceModel mechanic : mechanicServiceModel) {
            mechanicSpecs = mechanic.getSpecialization();
        }
        for (Services problems : carProblems) {
            for (Services specialization : mechanicSpecs) {
                if (problems.equals(specialization)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public MechanicInfoForCommentsServiceModel findCurrentMechanic(Long id) {
        MechanicEntity mechanic = findEntityById(id);
        return new MechanicInfoForCommentsServiceModel(mechanic.getId(), mechanic.getFullName(), mechanic.getImageUrl());
    }

    @Override
    public boolean checkMechanicFields(AddMechanicBindingModel addMechanicBindingModel) {
        List<MechanicEntity> allMechanics = this.mechanicRepository.findAll();
        if (allMechanics != null && !allMechanics.isEmpty()) {
            for (MechanicEntity mechanic : allMechanics) {
                if (mechanic.getPhoneNumber().equals(addMechanicBindingModel.getPhoneNumber())) {
                    return false;
                }
            }
        }
        return true;
    }
}
