package com.project.autoKings.service;

import com.project.autoKings.model.binding.RegisterUserBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.UserEntity;
import com.project.autoKings.model.service.EditUserProfileServiceModel;
import com.project.autoKings.model.service.UserServiceModel;
import com.project.autoKings.model.view.CustomerViewModel;
import com.project.autoKings.model.view.EditUserProfileViewModel;

import java.security.Principal;
import java.util.List;

public interface UserService {

    void saveToDb(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);

    List<CustomerViewModel> mapCustomerInfo();

    UserServiceModel findByUsername(String name);

    void deleteUser(Long id);

    boolean isAdmin(Principal principal);
    
    EditUserProfileViewModel findUserToEdit(Long id);

    void editUserProfile(Long id, EditUserProfileServiceModel editUserProfileServiceModel);

    boolean checkUserFields(RegisterUserBindingModel registerUserBindingModel);
}
