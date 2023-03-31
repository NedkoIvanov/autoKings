package com.project.autoKings.service.implementation;

import com.project.autoKings.model.binding.AddCarBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.entity.UserEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.*;
import com.project.autoKings.model.view.EditCarViewModel;
import com.project.autoKings.repository.CarRepository;
import com.project.autoKings.repository.MechanicRepository;
import com.project.autoKings.repository.UserRepository;
import com.project.autoKings.service.CarService;
import com.project.autoKings.service.ServicesService;
import com.project.autoKings.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final MechanicRepository mechanicRepository;

    private final ModelMapper modelMapper;

    private final ServicesService servicesService;

    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository,
                          MechanicRepository mechanicRepository, ModelMapper modelMapper, ServicesService servicesService) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.servicesService = servicesService;
    }

    @Override
    @Transactional
    public CarEntity saveCarToDb(CarServiceModel carServiceModel,
                            String username,
                            List<Services> services) {
        CarEntity car = new CarEntity();
        car.setMake(carServiceModel.getMake());
        car.setModel(carServiceModel.getModel());
        car.setImageUrl(carServiceModel.getImageUrl());
        car.setReleased(carServiceModel.getReleased());
        car.setMileage(carServiceModel.getMileage());
        UserEntity owner = this.userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User with requested name: " + username + " not found!"));
        car.setOwner(owner);
        owner.addCar(car);
        car.setVIN(carServiceModel.getVIN());
        car.setEngine(carServiceModel.getEngine());
        car.setTransmission(carServiceModel.getTransmission());
        List<ServiceEntity> serviceEntities = this.servicesService.findBySpecialization(services);
        car.setProblem(serviceEntities);
        car.setAdded(LocalDate.now());
        car.setProblemDescription(carServiceModel.getProblemDescription());
        car.setAccepted(false);
        return this.carRepository.save(car);
    }
    @Override
    public CarEntity findCarEntityById(Long id) {
        return this.carRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Object with requested id:" + id + " not found!")
        );
    }

    @Override
    public List<CarInfoServiceModel> findAllCars() {
        List<CarEntity> allCars = this.carRepository.findAll();
        List<CarInfoServiceModel> carInfoModel = new ArrayList<>();
        List<CarServiceModel> carServiceModel = new ArrayList<>();
        if(allCars==null){
           throw new ObjectNotFoundException("No cars recorded to database");
        }else{
           carInfoModel = this.modelMapper.map(this.mapEntity(allCars,carServiceModel),
                   new TypeToken<List<CarInfoServiceModel>>(){}.getType());
           LinkedHashSet<String> mechanicNames = new LinkedHashSet<>();

           for(int i=0;i<carInfoModel.size();i++){
               carInfoModel
                       .get(i)
                       .setFullName(allCars.get(i).getOwner().getFullName());
               carInfoModel
                       .get(i)
                       .setPhoneNumber(allCars.get(i).getOwner().getPhoneNumber());
               List<String> mechanicNamesList = allCars.get(i).mechanicNames();
               if(mechanicNamesList!=null && !mechanicNamesList.isEmpty()){
                   mechanicNames.addAll(mechanicNamesList);
               }
               carInfoModel
                       .get(i)
                       .setMechanicFullName(mechanicNames);
               carInfoModel
                       .get(i)
                       .setAccepted(allCars.get(i).getAccepted());

           }
           return carInfoModel;
        }

    }

    @Override
    public CarServiceModel findNewestCar(String ownerUserName) {
        List<CarEntity> ownedCars = this.carRepository.findAllByOwner(this.userRepository.findByUsername(ownerUserName)
                .orElseThrow(()->new UsernameNotFoundException("Owner with requested name: " + ownerUserName + " not found!")));
        List<CarServiceModel> carServiceModel = new ArrayList<>();
        if(!ownedCars.isEmpty()){
            carServiceModel =  mapEntity(ownedCars,carServiceModel);
            return carServiceModel.get(carServiceModel.size()-1);
        }else{
            //exception
            return null;
        }
    }

    @Override
    public void setRepairmentDate(SetDateServiceModel setDateServiceModel,Long id) {
        CarEntity car = findCarEntityById(id);
        LocalDate release = setDateServiceModel.getRepairmentDate();
        car.setRepairmentDays(release);
        this.carRepository.save(car);

    }


    private  void carIsRepaired(LocalDate date) {
        List<CarEntity> cars = this.carRepository.findAllByRepairmentDays(date);
        if(cars!=null && !cars.isEmpty()){
            for(CarEntity car : cars){
                car.setRepairmentDays(null);
                car.setAccepted(false);
                car.setProblemDescription(null);
                List<MechanicEntity> mechanics = car.getMechanics();
                for (MechanicEntity mechanic : mechanics) {
                    mechanic.setChosen(mechanic.getChosen() - 1);
                    this.mechanicRepository.save(mechanic);
                }
                car.setMechanics(null);
            }
            this.carRepository.saveAll(cars);
        }

    }

    @Override
    @Scheduled(cron = "0 15 17 * * ?")
    public void carIsRepairedScheduler(){
        LocalDate date = LocalDate.now();
        carIsRepaired(date);
    }


    @Override
    public void removeCurrentRepairmentDate(Long id) {
        CarEntity car = findCarEntityById(id);
        car.setRepairmentDays(null);
        this.carRepository.save(car);
    }

    @Override
    public void removeProblems(CarServiceModel carServiceModel, MechanicEntity mechanic) {
        CarEntity currentCar = this.findCarEntityById(carServiceModel.getId());
        currentCar.getMechanics().add(mechanic);
        currentCar.getProblem().remove(0);
        if(currentCar.getMechanics()!=null && !currentCar.getMechanics().isEmpty() && currentCar.getProblem().isEmpty()){
                currentCar.setAccepted(true);
        }
        this.carRepository.save(currentCar);
    }

    @Override
    public void addNewProblems(Long id, AddNewProblemServiceModel addNewProblemServiceModel,List<Services> services) {
        CarEntity car = this.findCarEntityById(id);
        List<ServiceEntity> problems = this.servicesService.findBySpecialization(services);
        car.setProblem(problems);
        car.setProblemDescription(addNewProblemServiceModel.getProblemDescription());
        this.carRepository.save(car);
    }

    @Override
    public boolean checkCarFields(AddCarBindingModel addCarBindingModel) {
        List<CarEntity> allCars = this.carRepository.findAll();
        if(allCars!=null && !allCars.isEmpty()){
            for(CarEntity car : allCars){
                if(car.getVIN().equals(addCarBindingModel.getVIN())){
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public List<CarInfoForUserServiceModel> findOwnedCarsByUsername(String username) {
        UserEntity user = this.userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Owner with requested name: " + username + " not found!"));
        List<CarInfoForUserServiceModel> carServiceModel = new ArrayList<>();
        List<CarEntity> ownedCars = user.getCars();
        LinkedHashSet<String> mechanicNames = new LinkedHashSet<>();
        if(ownedCars!=null && !ownedCars.isEmpty()){
            for(int i=0;i<ownedCars.size();i++){
                List<String> mechanicNamesList = ownedCars.get(i).mechanicNames();
                if(mechanicNamesList!=null && !mechanicNamesList.isEmpty()) {
                        mechanicNames.addAll(mechanicNamesList);
                }
                carServiceModel.add(
                        new CarInfoForUserServiceModel(
                                ownedCars.get(i).getId(),
                                ownedCars.get(i).getMake(),
                                ownedCars.get(i).getModel(),
                                ownedCars.get(i).getImageUrl(),
                                mechanicNames,
                                ownedCars.get(i).getAccepted(),
                                ownedCars.get(i).getRepairmentDays(),
                                ownedCars.get(i).getServices(),
                                ownedCars.get(i).getOwner().getUsername()
                    ));
                }
            }
        return carServiceModel;
    }

    @Override
    public List<CarEntity> findCarsEntityByOwner(UserEntity owner) {
       return this.carRepository.findAllByOwner(owner);
    }

    @Override
    public void deleteCar(Long id) {
        if(findCarEntityById(id)!=null){
            this.carRepository.deleteById(id);
        }
    }

    @Override
    public EditCarViewModel findCarViewToEditById(Long id) {
        return this.modelMapper.map(findCarEntityById(id), EditCarViewModel.class);

    }

    @Override
    public void editCar(Long id,EditCarServiceModel editCarServiceModel) {

        CarEntity car = findCarEntityById(id);
        car.setMake(editCarServiceModel.getMake());
        car.setModel(editCarServiceModel.getModel());
        car.setImageUrl(editCarServiceModel.getImageUrl());
        car.setReleased(editCarServiceModel.getReleased());
        car.setMileage(editCarServiceModel.getMileage());
        car.setVIN(editCarServiceModel.getVIN());
        car.setEngine(editCarServiceModel.getEngine());
        car.setTransmission(editCarServiceModel.getTransmission());

        this.carRepository.save(car);
    }

    private List<CarServiceModel> mapEntity(List<CarEntity> source,List<CarServiceModel> destination){

        if(source!=null && !source.isEmpty()){
            for(int i=0;i<source.size();i++){

                destination.add(
                        new CarServiceModel(
                                source.get(i).getId(),
                                source.get(i).getMake(),
                                source.get(i).getModel(),
                                source.get(i).getImageUrl(),
                                source.get(i).getMileage(),
                                source.get(i).getEngine(),
                                source.get(i).getTransmission(),
                                source.get(i).getReleased(),
                                source.get(i).getVIN(),
                                source.get(i).getProblemDescription(),
                                source.get(i).getServices(),
                                source.get(i).getRepairmentDays()
                        ));

            }
        }
        return destination;
    }

}
