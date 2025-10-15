package com.nova.blog.controller;

import com.nova.blog.payload.JwtAuthRequest;
import com.nova.blog.payload.JwtAuthResponse;
import com.nova.blog.payload.UserDTO;
import com.nova.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) {
        JwtAuthResponse jwtAuthResponse = authService.login(jwtAuthRequest);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        UserDTO userResponseDTO = authService.signup(userDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

}
