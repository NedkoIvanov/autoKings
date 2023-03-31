package com.project.autoKings.service;

import com.project.autoKings.model.entity.RoleEntity;

public interface RoleService {

    RoleEntity findById(Long id);

    void checkAndSeedRoles();
}
