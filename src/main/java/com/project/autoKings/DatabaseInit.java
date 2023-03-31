package com.project.autoKings;

import com.project.autoKings.service.RoleService;
import com.project.autoKings.service.ServicesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final ServicesService servicesService;
    private final RoleService roleService;


    public DatabaseInit(ServicesService servicesService, RoleService roleService) {
        this.servicesService = servicesService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.servicesService.checkAndSeed();
        this.roleService.checkAndSeedRoles();
    }
}
