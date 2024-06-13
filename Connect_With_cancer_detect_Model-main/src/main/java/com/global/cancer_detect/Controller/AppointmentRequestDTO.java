package com.global.cancer_detect.Controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Setter
@Getter
public class AppointmentRequestDTO {

    String day;
    String date;
//    Boolean cancle;
//    Boolean confirmed;
    String fromm;
    String too;
    Long userId;
    Long doctorId;

}
