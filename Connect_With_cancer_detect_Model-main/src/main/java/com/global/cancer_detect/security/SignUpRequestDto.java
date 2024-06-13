package com.global.cancer_detect.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
