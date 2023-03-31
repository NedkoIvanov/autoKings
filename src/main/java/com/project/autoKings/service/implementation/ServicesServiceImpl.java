package com.project.autoKings.service.implementation;

import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.EditServiceServiceModel;
import com.project.autoKings.model.service.ServiceServiceModel;
import com.project.autoKings.model.view.ServiceTypesViewModel;
import com.project.autoKings.model.view.ServiceViewModel;
import com.project.autoKings.repository.ServiceRepository;
import com.project.autoKings.service.ServicesService;
import com.project.autoKings.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {
    private final ServiceRepository serviceRepository;

    private final ModelMapper modelMapper;

    public ServicesServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void checkAndSeed() {

        long count = this.serviceRepository.count();
        if(count!=0){
            return;
        }
        List<ServiceEntity> services = new ArrayList<>();
        List<Services> enums = Arrays.asList(Services.values());
        for(int i=0;i<enums.size();i++) {
            services.add(new ServiceEntity(enums.get(i)));
        }
        this.serviceRepository.saveAll(services);
    }

    @Override
    public List<ServiceServiceModel> findAllServices() {
        List<ServiceEntity> services = this.serviceRepository.findAll();
        if(services==null){
            //todo implement objectnotfoundexception
            return null;
        }else{
            return this.modelMapper.map(services, new TypeToken<List<ServiceServiceModel>>(){}.getType());
        }
    }

    @Override
    public ServiceViewModel findById(Long id) {
        ServiceEntity service = this.findEntityById(id);
        return this.modelMapper.map(service,ServiceViewModel.class);
    }

    @Override
    public void editService(Long id,EditServiceServiceModel editServiceServiceModel) {
        ServiceEntity service = this.findEntityById(id);
        service.setDescription(editServiceServiceModel.getDescription());
        service.setImageUrl(editServiceServiceModel.getImageUrl());
        service.setTitle(editServiceServiceModel.getTitle());
        this.serviceRepository.save(service);
    }

    @Override
    public ServiceEntity findEntityById(Long id) {
        return this.serviceRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Object with requested id:" + id + " not found!")
        );
    }

    @Override
    public List<ServiceEntity> findBySpecialization(List<Services> services) {
        List<ServiceEntity> serviceEntity = new ArrayList<>();
        for(int i=0;i<services.size();i++){
            serviceEntity.add(this.serviceRepository.findByServices(services.get(i)));
        }
        if(serviceEntity==null){
            throw new ObjectNotFoundException("Object with requested services not found");
        }

        return serviceEntity;

    }

    @Override
    public List<ServiceTypesViewModel> findByCarProblems(List<Services> problems) {
        List<ServiceEntity> serviceEntity = findBySpecialization(problems);

        return this.modelMapper.map(serviceEntity,new TypeToken<List<ServiceTypesViewModel>>(){}.getType());

    }

}
