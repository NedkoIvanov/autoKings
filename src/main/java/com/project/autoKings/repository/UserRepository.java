package com.project.autoKings.repository;

import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

}
