package com.project.autoKings.service.implementation;

import com.project.autoKings.model.binding.RegisterUserBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.RoleEntity;
import com.project.autoKings.model.entity.UserEntity;
import com.project.autoKings.model.enums.Role;
import com.project.autoKings.model.service.EditServiceServiceModel;
import com.project.autoKings.model.service.EditUserProfileServiceModel;
import com.project.autoKings.model.service.UserServiceModel;
import com.project.autoKings.model.view.CustomerViewModel;
import com.project.autoKings.model.view.EditUserProfileViewModel;
import com.project.autoKings.repository.UserRepository;
import com.project.autoKings.service.AutoKingsUserDetailsServiceImpl;
import com.project.autoKings.service.CarService;
import com.project.autoKings.service.RoleService;
import com.project.autoKings.service.UserService;
import com.project.autoKings.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AutoKingsUserDetailsServiceImpl autoKingsUserDetailsService;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final CarService carService;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, AutoKingsUserDetailsServiceImpl autoKingsUserDetailsService,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder, CarService carService, RoleService roleService) {
        this.userRepository = userRepository;
        this.autoKingsUserDetailsService = autoKingsUserDetailsService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.carService = carService;
        this.roleService = roleService;
    }



    //TODO check if there is a user with the same username in the database

    @Override
    @Transactional
    public void saveToDb(UserServiceModel userServiceModel) {
         UserEntity user = new UserEntity();
         user.setFullName(userServiceModel.getFullName());
         user.setAge(userServiceModel.getAge());
         user.setEmail(userServiceModel.getEmail());
         user.setPassword(
                this.passwordEncoder.encode(userServiceModel.getPassword()));
         user.setImageUrl(userServiceModel.getImageUrl());
         user.setUsername(userServiceModel.getUsername());
         RoleEntity adminRole = this.roleService.findById(1L);
         RoleEntity userRole = this.roleService.findById(2L);
         if(this.userRepository.count()==0){
             user.setRoles(List.of(adminRole,userRole));
         }else{
             user.setRoles(List.of(userRole));
         }
         user.setPhoneNumber(userServiceModel.getPhoneNumber());
         user.setRegisteredOn(LocalDate.now());
         this.userRepository.save(user);

         UserDetails userDetails = this.autoKingsUserDetailsService.loadUserByUsername(user.getUsername());
         Authentication auth = new UsernamePasswordAuthenticationToken(
                 userDetails,user.getPassword(),userDetails.getAuthorities());
         SecurityContextHolder.getContext().setAuthentication(auth);

    }

    @Override
    public UserServiceModel findById(Long id) {

        UserEntity user = this.userRepository.findById(id).orElseThrow(
                        () -> new ObjectNotFoundException("User with requested id:" + id + " not found!")
                );

        return this.modelMapper.map(user,UserServiceModel.class);

    }

    @Override
    public List<CustomerViewModel> mapCustomerInfo() {

       List<UserEntity> allUsers = this.userRepository.findAll();
       CustomerViewModel customerViewModel = new CustomerViewModel();
       if(allUsers.isEmpty()){
           return null;
       }else {

           List<CustomerViewModel> customerViewModelList = new ArrayList<>();
           //iterate from i=1, because where i=0, customer is ADMIN!
           for (int i = 1; i < allUsers.size(); i++) {
               customerViewModelList.add(
                       new CustomerViewModel(
                               allUsers.get(i).getId(),
                               allUsers.get(i).getUsername(),
                               allUsers.get(i).getFullName(),
                               allUsers.get(i).getEmail(),
                               allUsers.get(i).getImageUrl()
                               )
               );
           }
           return customerViewModelList;
       }
    }

    @Override
    public UserServiceModel findByUsername(String name) {
        UserEntity user = this.userRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User with requested name:" + name + " not found!")
        );

        return this.modelMapper.map(user, UserServiceModel.class);

    }

    @Override
    public void deleteUser(Long id) {
        if(this.userRepository.findById(id)==null && this.userRepository.findById(id).isEmpty()){
            throw new ObjectNotFoundException("User with requested id:" + id + " not found!");
        }
        this.userRepository.deleteById(id);

    }


    @Override
    public boolean isAdmin(Principal principal) {
        return principal != null && principal instanceof Authentication &&
                ((Authentication) principal).getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

    }


    @Override
    public EditUserProfileViewModel findUserToEdit(Long id) {

        //I don't check if user is present, because if I'm reaching this method it is certain that there is such a user
        return this.modelMapper.map(this.userRepository.findById(id).get(), EditUserProfileViewModel.class);

    }

    @Override
    public void editUserProfile(Long id, EditUserProfileServiceModel editUserProfileServiceModel) {

        UserEntity user = this.userRepository.findById(id).get();
        user.setFullName(editUserProfileServiceModel.getFullName());
        user.setPhoneNumber(editUserProfileServiceModel.getPhoneNumber());
        user.setImageUrl(editUserProfileServiceModel.getImageUrl());
        user.setEmail(editUserProfileServiceModel.getEmail());
        user.setAge(editUserProfileServiceModel.getAge());
        user.setUsername(editUserProfileServiceModel.getUsername());
        this.userRepository.save(user);

    }

    @Override
    public boolean checkUserFields(RegisterUserBindingModel registerUserBindingModel) {
        List<UserEntity> allUsers = this.userRepository.findAll();
        if(allUsers!=null && !allUsers.isEmpty()){
            for(UserEntity user : allUsers){
                if(user.getUsername().equals(registerUserBindingModel.getUsername()) ||
                user.getPassword().equals(registerUserBindingModel.getPassword()) ||
           //     user.getPhoneNumber().equals(registerUserBindingModel.getPhoneNumber()) ||
                user.getEmail().equals(registerUserBindingModel.getPhoneNumber())){
                    return false;
                }
            }
        }
        return true;
    }
}
