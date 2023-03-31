package com.project.autoKings.repository;

import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.enums.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepository extends JpaRepository<MechanicEntity, Long> {

    @Query("SELECT m FROM MechanicEntity m JOIN m.specialization s WHERE s.services = :specializationName")
    List<MechanicEntity> findAllBySpecialization(@Param("specializationName") Services specializationName);

}
