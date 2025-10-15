package com.nova.blog.service;

import com.nova.blog.payload.JwtAuthRequest;
import com.nova.blog.payload.JwtAuthResponse;
import com.nova.blog.payload.UserDTO;

public interface AuthService {

    JwtAuthResponse login(JwtAuthRequest jwtAuthRequest);

    UserDTO signup(UserDTO userDTO);
}
