package com.project.autoKings.web;

import com.project.autoKings.model.binding.EditUserProfileBindingModel;
import com.project.autoKings.model.binding.RegisterUserBindingModel;
import com.project.autoKings.model.service.EditUserProfileServiceModel;
import com.project.autoKings.model.service.UserServiceModel;
import com.project.autoKings.model.view.CustomerViewModel;
import com.project.autoKings.model.view.UserProfileViewModel;
import com.project.autoKings.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    //REGISTER

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("user")){
            model.addAttribute("user",new RegisterUserBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterUserBindingModel registerUserBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() ||
                !registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword()) ||
                !this.userService.checkUserFields(registerUserBindingModel)
        ) {
            redirectAttributes
                    .addFlashAttribute("user",registerUserBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerUserBindingModel"
                            , bindingResult);
           return "redirect:register";
        }

        UserServiceModel userToSave = this.modelMapper.map(registerUserBindingModel, UserServiceModel.class);

        this.userService.saveToDb(userToSave);

        return "redirect:login";
    }


    //LOGIN

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/login-error")
    public String login(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                        String username,
                        RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("badCredentials",true);
        redirectAttributes.addFlashAttribute("username",username);
        return "redirect:/users/login";
    }


    //USER PROFILE

    @GetMapping("/profile/{name}")
    public String getProfile(@PathVariable String name,Model model){
        model.addAttribute("userProfile",
                this.modelMapper.map(this.userService.findByUsername(name),UserProfileViewModel.class));
        return "profile";
    }


    //CUSTOMERS INFO FOR ADMIN

    @GetMapping("/customer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCustomerInfo(Model model){
        List<CustomerViewModel> customerView = this.userService.mapCustomerInfo();
        if(customerView==null && customerView.isEmpty()){
            model.addAttribute("noCustomers",true);
        }
        model.addAttribute("customer",this.userService.mapCustomerInfo());
        return "customer-info";
    }

    //DELETE CUSTOMER(role==ADMIN)
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCustomer(@PathVariable Long id,Model model){
        this.userService.deleteUser(id);
        return "redirect:/users/customer";
    }


    //UPDATE USER PROFILE
    @GetMapping("/{id}/edit")
    public String getUserEdit(@PathVariable Long id,Model model){

        EditUserProfileServiceModel editUser = this.modelMapper.map(
                this.userService.findUserToEdit(id), EditUserProfileServiceModel.class);
        model.addAttribute("userEdit",editUser);
        return "user-edit";
    }

    @PatchMapping("/{id}/edit")
    public String editUser(@PathVariable Long id,
                           @ModelAttribute @Valid EditUserProfileBindingModel editUserProfileBindingModel,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/users/{id}/edit";
        }
        EditUserProfileServiceModel editUserProfileServiceModel =
                this.modelMapper.map(editUserProfileBindingModel,EditUserProfileServiceModel.class);
        this.userService.editUserProfile(id,editUserProfileServiceModel);
        return "redirect:/users/profile/" + editUserProfileBindingModel.getUsername();
    }

}
