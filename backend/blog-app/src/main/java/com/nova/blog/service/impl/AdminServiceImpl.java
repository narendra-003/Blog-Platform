package com.nova.blog.service.impl;

import com.nova.blog.config.AppConstants;
import com.nova.blog.exception.ApiException;
import com.nova.blog.exception.ResourceNotFoundException;
import com.nova.blog.model.Role;
import com.nova.blog.model.User;
import com.nova.blog.repository.RoleRepository;
import com.nova.blog.repository.UserRepository;
import com.nova.blog.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void promoteToAdmin(Integer userID) {

        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userID));

        Role adminRole = roleRepository.findByName(AppConstants.ROLE_ADMIN).orElseThrow(() -> new ApiException("ROLE_ADMIN not found"));

        if (!user.getRoles().contains(adminRole)) {
            user.getRoles().add(adminRole);
            userRepository.save(user);
        } else {
            throw new ApiException("User is already an admin.");
        }
    }
}
