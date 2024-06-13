package com.global.cancer_detect.Controller;

import com.global.cancer_detect.service.RoleService;
import com.global.cancer_detect.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
//اشغلو لما ارن الفرونت
@CrossOrigin()
@AllArgsConstructor
@NoArgsConstructor
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public ResponseEntity<?>findAll(){
       return ResponseEntity.ok( roleService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        return ResponseEntity.ok( roleService.findById(id));
    }
}
