package com.global.cancer_detect.security;

import com.global.cancer_detect.Entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JWTResponseDto {

    private String accessToken;

    private String refreshToken;
    private User user;

}
