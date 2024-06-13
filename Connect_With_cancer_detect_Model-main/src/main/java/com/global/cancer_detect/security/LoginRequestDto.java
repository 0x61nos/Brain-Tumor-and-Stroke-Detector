package com.global.cancer_detect.security;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @NotEmpty
    @Email        //اشغلها وانا بتيست
    private String email;

    @NotEmpty
    private String password;
}
