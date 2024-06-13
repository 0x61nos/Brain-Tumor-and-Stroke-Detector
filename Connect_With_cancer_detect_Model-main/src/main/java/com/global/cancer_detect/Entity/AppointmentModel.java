package com.global.cancer_detect.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
//    private Date date;
    private String datee;
    private Boolean cancle=null;
    private Boolean confirmed=null;
//    private String cancle;
//    private String confirmed;
    private String fromm;
    private String too;
    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnore
    @JoinTable(name = "user_Appointment",
            joinColumns =@JoinColumn(name = "appointment_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    @OrderColumn(name = "id")
//    --------------------------------------------------------------------------------
//    @ManyToMany(mappedBy = "appointments")
////    @JsonIgnore
    private List<User> user;


}
