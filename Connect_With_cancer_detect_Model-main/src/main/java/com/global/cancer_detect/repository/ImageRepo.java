package com.global.cancer_detect.repository;

import com.global.cancer_detect.Entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageModel,Long> {

}
