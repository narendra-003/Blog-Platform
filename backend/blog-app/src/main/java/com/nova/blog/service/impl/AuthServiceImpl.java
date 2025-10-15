package com.nova.blog.service.impl;

import com.nova.blog.config.AppConstants;
import com.nova.blog.exception.ApiException;
import com.nova.blog.exception.EmailAlreadyExistException;
import com.nova.blog.mapper.UserMapper;
import com.nova.blog.model.Role;
import com.nova.blog.model.User;
import com.nova.blog.payload.JwtAuthRequest;
import com.nova.blog.payload.JwtAuthResponse;
import com.nova.blog.payload.UserDTO;
import com.nova.blog.repository.RoleRepository;
import com.nova.blog.repository.UserRepository;
import com.nova.blog.security.AuthUtil;
import com.nova.blog.security.CustomUserDetailService;
import com.nova.blog.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, AuthUtil authUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.authUtil = authUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public JwtAuthResponse login(JwtAuthRequest jwtAuthRequest) {

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUser(), jwtAuthRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new ApiException("Invalid Username or Password !!!");
        }


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        String token = authUtil.generateJwtToken(userEmail);
        return new JwtAuthResponse(token);
    }

    @Override
    public UserDTO signup(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistException("A user with this email already exists " + userDTO.getEmail());
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User newUser = UserMapper.toModel(userDTO);

        Role roleNormal = roleRepository.findByName(AppConstants.ROLE_NORMAL)
                .orElseThrow(() -> new ApiException("ROLE_NORMAL not found"));

        newUser.getRoles().add(roleNormal);

        User savedUser = userRepository.save(newUser);
        return UserMapper.toDTO(savedUser);
    }
}
