package com.global.cancer_detect.service;

import com.global.cancer_detect.Entity.AppointmentModel;
import com.global.cancer_detect.Entity.User;
import com.global.cancer_detect.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private UserService userService;
    public ResponseEntity<List<AppointmentModel>> findAllAppointmentByDoctorId(Long id){
        return ResponseEntity.ok(appointmentRepo.findByUserId(id));
    }
    public ResponseEntity<List<AppointmentModel>> findAllAppointmentByUserIdAndRoleName(Long id){
        return ResponseEntity.ok(appointmentRepo.findByUserIdAndUserRolesName(id,"doctor"));
    }
    public ResponseEntity<List<AppointmentModel>> findAllAppointments(){
        return ResponseEntity.ok(appointmentRepo.findAll());
    }
    public ResponseEntity<Optional<AppointmentModel>> findAppointmentById(Long id){
        return ResponseEntity.ok(appointmentRepo.findById(id));
    }
    public ResponseEntity<AppointmentModel> saveAppointment(String day,String date,String fromm,String too,Long userId,Long doctorId){
        User user =userService.findById(userId);
        System.out.println(user);
        User doctor= userService.findById(doctorId);
        System.out.println(doctor);
        List<User>appointUserAndDoctor=new ArrayList<>();
        appointUserAndDoctor.add(user);
        appointUserAndDoctor.add(doctor);
        Set<AppointmentModel>appointmentModels=new HashSet<>();
        AppointmentModel appointmentModel1=new AppointmentModel(null,day,date,null,null,fromm,too,appointUserAndDoctor);

//        AppointmentModel appointmentModel2= appointmentRepo.save(appointmentModel1);
//        appointmentRepo.save(appointmentModel1);
//        appointmentModels.add(appointmentRepo.getById());
//        user.setAppointments(appointmentModels);
//        doctor.setAppointments(appointmentModels);
//        userService.save(user);
//        userService.save(doctor);
//        System.out.println(user);
//        return ResponseEntity.ok(appointmentRepo.getById(appointmentModel2.getId()));
        return ResponseEntity.ok(appointmentRepo.save(appointmentModel1));
    }
    public ResponseEntity<AppointmentModel> cancelAppointmentByid(Long id){
        AppointmentModel appointmentModel=appointmentRepo.getById(id);
        appointmentModel.setCancle(true);
        return ResponseEntity.ok(appointmentRepo.save(appointmentModel));
    }
    public ResponseEntity<AppointmentModel> confirmAppointmentByid(Long id){
        AppointmentModel appointmentModel=appointmentRepo.getById(id);
        appointmentModel.setConfirmed(true);
        return ResponseEntity.ok(appointmentRepo.save(appointmentModel));
    }



}
