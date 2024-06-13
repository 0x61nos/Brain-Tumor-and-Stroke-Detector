package com.global.cancer_detect.security;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<JWTResponseDto> login (@RequestBody LoginRequestDto loginRequest){

        JWTResponseDto jwtResponseDto = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(jwtResponseDto);
    }
    @PostMapping("/signUp")
    public ResponseEntity<?>signUp(@RequestBody SignUpRequestDto signupRequest){

        JWTResponseDto jwtResponseDto = authService.signUp(signupRequest.getFirstName(),signupRequest.getLastName(),signupRequest.getEmail(), signupRequest.getPassword());

        return ResponseEntity.ok(jwtResponseDto);
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<AccessTokenDto> refreshAccessToken(@RequestParam String refreshToken) {

        AccessTokenDto dto = authService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(dto);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken) {

        authService.logoutUser(refreshToken);

        return ResponseEntity.ok(null);
    }
}
