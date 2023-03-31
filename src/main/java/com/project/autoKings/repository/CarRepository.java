package com.project.autoKings.repository;

import com.project.autoKings.model.entity.CarEntity;
import com.project.autoKings.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity,Long> {

    List<CarEntity> findAllByOwner(UserEntity owner);

    List<CarEntity> findAllByRepairmentDays(LocalDate date);
}
