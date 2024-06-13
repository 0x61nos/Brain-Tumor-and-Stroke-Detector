package com.global.cancer_detect.service;

import com.global.cancer_detect.Entity.RoleModel;
import com.global.cancer_detect.Entity.User;
import com.global.cancer_detect.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;
    public List<RoleModel> findAll(){
        return roleRepo.findAll();
    }
    public RoleModel findById(Long id){
        return roleRepo.findById(id).orElse(null);
    }

    public RoleModel findByName(String name){
        return roleRepo.findByName(name);
    }
    public RoleModel save(RoleModel entity){
        return roleRepo.save(entity);
    }
}
