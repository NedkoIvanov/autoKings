package com.project.autoKings.web;

import com.project.autoKings.model.binding.AddMechanicBindingModel;
import com.project.autoKings.model.binding.MechanicUpdateBindingModel;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.CarServiceModel;
import com.project.autoKings.model.service.MechanicServiceModel;
import com.project.autoKings.model.service.UpdateMechanicServiceModel;
import com.project.autoKings.model.view.MechanicViewModel;
import com.project.autoKings.service.CarService;
import com.project.autoKings.service.MechanicService;
import com.project.autoKings.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/mechanic")
public class MechanicsController {

    private final MechanicService mechanicService;

    private final ModelMapper modelMapper;

    private final CarService carService;

    private final UserService userService;

    public MechanicsController(MechanicService mechanicService,
                               ModelMapper modelMapper, CarService carService, UserService userService) {
        this.mechanicService = mechanicService;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getMechanicAddPage(Model model){
        if(!model.containsAttribute("mechanic")){
            model.addAttribute("mechanic",new AddMechanicBindingModel());
        }
        return "add-mechanic";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMechanic(@ModelAttribute @Valid AddMechanicBindingModel addMechanicBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("specialization") Services[] specialization){
        if(bindingResult.hasErrors() || !this.mechanicService.checkMechanicFields(addMechanicBindingModel)){
            redirectAttributes
                    .addFlashAttribute("mechanic",addMechanicBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addMechanicBindingModel",
                            bindingResult);
            return "redirect:/mechanic/add";
        }

        List<Services> specializations = new ArrayList<>();
        for(Services s : specialization){
            specializations.add(s);
        }
        MechanicServiceModel mechanics = this.modelMapper.map(addMechanicBindingModel,MechanicServiceModel.class);
        this.mechanicService.saveToDb(mechanics,specializations);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String getMechanicInfo(Model model){
        List<MechanicServiceModel> allMechanics = this.mechanicService.findAllMechanics();
        if(allMechanics!=null) {
            model.addAttribute("mechanics",
                    this.modelMapper.map(allMechanics, new TypeToken<List<MechanicViewModel>>() {
                    }.getType()));
            return "mechanic-info";
        }else{
            return "redirect:/services";
        }
    }

    @GetMapping("/services/{specialization}")
    public String getMechanicWithSpecs(@PathVariable String specialization,Model model,Principal principal){
        List<MechanicServiceModel> mechanicServiceModel = this.mechanicService.findAllMechanicsBySpecs(specialization);
        CarServiceModel newestCar = this.carService.findNewestCar(principal.getName());
        if(mechanicServiceModel == null){
            model.addAttribute("noMechanic",true);
        }else {
            model.addAttribute("mechanicSpecs",
                    this.modelMapper.map(
                            mechanicServiceModel,
                            new TypeToken<List<MechanicViewModel>>() {
                            }.getType()));
            if(!this.mechanicService.checkIfAllAreActive(mechanicServiceModel)){
                model.addAttribute("noActiveMechanics",true);
            }

            if(newestCar!=null){
                if(newestCar.getServices()!=null && !newestCar.getServices().isEmpty()
                        && this.mechanicService.isResponsible(newestCar,mechanicServiceModel)){
                    model.addAttribute("canChoose",true);
                }
            }

        }
        return "mechanics";
    }


    @PostMapping("/{id}/choose")
    public String chooseMechanic(@PathVariable Long id,
                                 Principal principal){

        CarServiceModel newestCar = this.carService.findNewestCar(principal.getName());
        this.mechanicService.chooseForServicing(id,newestCar);

        if(newestCar==null){
            return "redirect:/";
        }else{
            return "redirect:/car/"+ newestCar.getId() + "/services";
        }

    }


    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String fireMechanic(@PathVariable Long id){
        this.mechanicService.deleteMechanic(id);
        return "redirect:/mechanic/info";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getUpdatePage(@PathVariable Long id,Model model){

        UpdateMechanicServiceModel updateMechanicServiceModel =
                this.modelMapper.map(
                        this.mechanicService.findById(id),
                        UpdateMechanicServiceModel.class
                );
        model.addAttribute("mechanicToUpdate",updateMechanicServiceModel);
        return "update-mechanic";
    }

    @PatchMapping("/{id}/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateMechanic(@PathVariable Long id,
            @ModelAttribute @Valid MechanicUpdateBindingModel mechanicUpdateBindingModel,
                                 BindingResult bindingResult,
                                 @RequestParam("specialization")Services[] services){
        if(bindingResult.hasErrors()){
            return "redirect:/mechanic/{id}/update";
        }

        List<Services> servicesToList = new ArrayList<>();
        for(Services s: services){
            servicesToList.add(s);
        }

        UpdateMechanicServiceModel updatedMechanic =
                this.modelMapper.map(mechanicUpdateBindingModel, UpdateMechanicServiceModel.class);

        this.mechanicService.updateMechanic(id,updatedMechanic,servicesToList);

        return "redirect:/mechanic/info";

    }

    @GetMapping("/{id}/comments")
    public String getCommentsForMechanic(@PathVariable Long id,Model model){
        model.addAttribute("mechanicReview",this.mechanicService.findCurrentMechanic(id));
        return "comments";
    }



}
