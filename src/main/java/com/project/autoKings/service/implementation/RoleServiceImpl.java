package com.project.autoKings.service.implementation;

import com.project.autoKings.model.entity.RoleEntity;
import com.project.autoKings.model.enums.Role;
import com.project.autoKings.repository.RoleRepository;
import com.project.autoKings.service.RoleService;
import com.project.autoKings.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import  java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity findById(Long id) {
        RoleEntity role = this.roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Object with requested id:" + id + " not found!")
        );

        return role;
    }

    @Override
    public void checkAndSeedRoles() {
        long count = this.roleRepository.count();
        if(count!=0){
            return;
        }
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity(Role.ADMIN));
        roles.add(new RoleEntity(Role.USER));
        this.roleRepository.saveAll(roles);
    }
}
