package com.global.cancer_detect.repository;

import com.global.cancer_detect.Entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel,Long> {
    RoleModel findByName (String name);
}
