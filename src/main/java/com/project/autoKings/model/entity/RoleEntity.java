package com.project.autoKings.model.entity;

import com.project.autoKings.model.entity.Base;
import com.project.autoKings.model.enums.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class RoleEntity extends Base {

    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleEntity(){}

    public RoleEntity(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
