package com.global.cancer_detect.service;

import com.global.cancer_detect.Entity.RoleModel;
import com.global.cancer_detect.Entity.User;
import com.global.cancer_detect.repository.UserRepo;
import com.global.cancer_detect.security.AppUserDetail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User>findAll(){
        return userRepo.findAll();
    }
    public User findById(Long id){
        return userRepo.findById(id).orElse(null);
    }
    public User save(User entity){
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepo.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("This User Not Found With select Email");
        }
//        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), getAuthorities(user.get()));
        return new AppUserDetail(user.get());
    }

    private static List<GrantedAuthority> getAuthorities(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
        }
        return authorities;
    }
    public ResponseEntity<List<User>>findUsersByRolesName(String Rolename){
        return ResponseEntity.ok( userRepo.findByRolesName(Rolename));
    }
}
