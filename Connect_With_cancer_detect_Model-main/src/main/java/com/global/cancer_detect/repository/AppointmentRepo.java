package com.global.cancer_detect.repository;

import com.global.cancer_detect.Entity.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentModel,Long>{
    List<AppointmentModel>findByUserId(Long id);
    List<AppointmentModel>findByUserIdAndUserRolesName(Long userId,String UserRolesName);
}
