package com.project.autoKings.repository;

import com.project.autoKings.model.entity.ServiceEntity;
import com.project.autoKings.model.enums.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    @Query("SELECT s FROM ServiceEntity s WHERE s.services = :specializationName")
    ServiceEntity findByServices(@Param("specializationName") Services specializationName);
}
