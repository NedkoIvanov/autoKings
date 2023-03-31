package com.project.autoKings.web;

import com.project.autoKings.model.binding.EditServiceBindingModel;
import com.project.autoKings.model.service.EditServiceServiceModel;
import com.project.autoKings.model.view.ServiceViewModel;
import com.project.autoKings.service.ServicesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServicesController {

    private final ServicesService servicesService;
    private final ModelMapper modelMapper;

    public ServicesController(ServicesService servicesService, ModelMapper modelMapper) {
        this.servicesService = servicesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/services")
    public String services(Model model){
        model.addAttribute("serviceView",this.modelMapper.map(
                this.servicesService.findAllServices(),new TypeToken<List<ServiceViewModel>>(){}.getType()));
        return "services";
    }

    @GetMapping("/services/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editServices(@PathVariable Long id,Model model){
        EditServiceServiceModel editService =
                this.modelMapper.map(
                        this.servicesService.findById(id), EditServiceServiceModel.class
                );
        model.addAttribute("serviceToEdit",editService);
        return "services-edit";
    }

    @PatchMapping("/services/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String doEditServices(@PathVariable Long id,
                                 EditServiceBindingModel editServiceBindingModel,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/services/{id}/edit";
        }

        EditServiceServiceModel editService = this.modelMapper.map(
                editServiceBindingModel, EditServiceServiceModel.class
        );

        this.servicesService.editService(id,editService);
        return "redirect:/services";
    }



}
