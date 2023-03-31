package com.project.autoKings.web;

import com.project.autoKings.model.binding.AddCarBindingModel;
import com.project.autoKings.model.binding.AddNewProblemsBindingModel;
import com.project.autoKings.model.binding.EditCarBindingModel;
import com.project.autoKings.model.binding.SetDateBindingModel;
import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.enums.Services;
import com.project.autoKings.model.service.*;
import com.project.autoKings.model.view.CarInfoForUserViewModel;
import com.project.autoKings.model.view.CarInfoViewModel;
import com.project.autoKings.service.CarService;
import com.project.autoKings.service.ServicesService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    private final UserService userService;

    private final ServicesService servicesService;

    private final ModelMapper modelMapper;

    public CarController(CarService carService, UserService userService,
                         ServicesService servicesService, ModelMapper modelMapper) {
        this.carService = carService;
        this.userService = userService;
        this.servicesService = servicesService;
        this.modelMapper = modelMapper;
    }


    // ADD CAR

    @GetMapping("/add")
    public String addCarGetTemplate(Model model){
        if(!model.containsAttribute("car")){
            model.addAttribute("car",new AddCarBindingModel());
        }
        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute @Valid AddCarBindingModel addCarBindingModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Principal principal,
                         @RequestParam("service") Services[] services
                         ){
        if(bindingResult.hasErrors() || !this.carService.checkCarFields(addCarBindingModel)){
            redirectAttributes
                    .addFlashAttribute("car",addCarBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCarBindingModel"
                            , bindingResult);
            return "redirect:/car/add";
        }


        List<Services> service = new ArrayList<>();
        for(Services s : services){
            service.add(s);
        }

        CarServiceModel addCar = this.modelMapper.map(addCarBindingModel, CarServiceModel.class);

        CarEntity car = this.carService.saveCarToDb(addCar, principal.getName(),service);

        return "redirect:/car/"+ car.getId() + "/services";
    }

    // GET CAR INFO
    @GetMapping("/info/{name}")
    public String carInfo(@PathVariable String name,Model model){

        List<CarInfoForUserServiceModel> carInfoServiceModel = this.carService.findOwnedCarsByUsername(name);
        if(carInfoServiceModel==null && carInfoServiceModel.isEmpty()){
            model.addAttribute("noCarsAdded",true);
        }else {
            List<CarInfoForUserViewModel> carInfoForUserViewModel =
                    this.modelMapper.map(carInfoServiceModel, new TypeToken<List<CarInfoForUserViewModel>>() {}.getType());
            model.addAttribute("car", carInfoForUserViewModel);
        }
        return "car-info";
    }


    //DELETE CAR
    @DeleteMapping("/{id}/delete")
    public String deleteCar(@PathVariable Long id,Principal principal){
        this.carService.deleteCar(id);
        if(this.userService.isAdmin(principal)){
            return "redirect:/car/all";
        }else{
            return "redirect:/car/info/" + principal.getName();
        }
    }


    //EDIT CAR
    @GetMapping("/{id}/edit")
    public String editCarGetTemplate(@PathVariable Long id,Model model){
       EditCarServiceModel editCarServiceModel =
                this.modelMapper.map(this.carService.findCarViewToEditById(id), EditCarServiceModel.class);
       model.addAttribute("carToEdit",editCarServiceModel);
        return "car-edit";
    }

    @PatchMapping("/{id}/edit")
    public String editCarTemplate(@PathVariable Long id,
                                  @Valid EditCarBindingModel editCarBindingModel,
                                  BindingResult bindingResult,
                                  Principal principal){
        if(bindingResult.hasErrors()){
            return "redirect:/car/{id}/edit";
        }

        EditCarServiceModel editcarServiceModel = this.modelMapper.map(editCarBindingModel,EditCarServiceModel.class);

        this.carService.editCar(id,editcarServiceModel);

        return "redirect:/car/info/" + principal.getName();

    }


    @GetMapping("/{id}/services")
    public String getCarProblems(@PathVariable Long id,Model model){
        List<Services> problems = this.carService.findCarEntityById(id).getServices();
        if(problems==null){
            model.addAttribute("mechanicsWereChosen",true);
        }else {
            model.addAttribute("carServiceView",
                    this.servicesService.findByCarProblems(problems));
        }
        return "problem-types";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllCars(Model model){
        model.addAttribute("allCars",this.modelMapper.map(
                this.carService.findAllCars(),new TypeToken<List<CarInfoViewModel>>(){}.getType()));
        return "car-all";
    }

    @ModelAttribute("addDate")
    public SetDateBindingModel getSetDateBindingModel() {
        return new SetDateBindingModel();
    }
    @PatchMapping("/{id}/repairment")
    public String setRepairmentDate(@PathVariable Long id,
                                    @Valid  SetDateBindingModel setDateBindingModel,
                                    BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            SetDateServiceModel setDateServiceModel = this.modelMapper.map(
                    setDateBindingModel, SetDateServiceModel.class);
            this.carService.setRepairmentDate(setDateServiceModel,id);
            this.carService.carIsRepairedScheduler();
        }
        return "redirect:/car/all";
    }

    @PatchMapping("/{id}/change")
    public String changeRepairmentDate(@PathVariable Long id){
        this.carService.removeCurrentRepairmentDate(id);
        return "redirect:/car/all";
    }


    @GetMapping("/{id}/add/problems")
    public String getProblemsTemplate(@PathVariable Long id,Model model){
        model.addAttribute("id",this.carService.findCarEntityById(id).getId());
        return "add-car-problems";
    }

    @ModelAttribute("carAddProblem")
    public AddNewProblemsBindingModel getAddNewProblemBindingModel() {
        return new AddNewProblemsBindingModel();
    }

    @PatchMapping("/{id}/add/problems")
    public String setProblems(@PathVariable Long id,
                              @Valid AddNewProblemsBindingModel addNewProblemsBindingModel,
                              BindingResult bindingResult,
                              @RequestParam("service") Services[] services){

        if(bindingResult.hasErrors()){
            return "redirect:/car/{id}/add/problems";
        }

        List<Services> service = new ArrayList<>();
        for(Services s : services){
            service.add(s);
        }

        AddNewProblemServiceModel addNewProblemsServiceModel =
                this.modelMapper.map(addNewProblemsBindingModel, AddNewProblemServiceModel.class);
        this.carService.addNewProblems(id,addNewProblemsServiceModel,service);

        return "redirect:/car/{id}/services";
    }


}
