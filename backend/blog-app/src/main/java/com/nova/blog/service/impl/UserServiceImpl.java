package com.nova.blog.service.impl;

import com.nova.blog.exception.EmailAlreadyExistException;
import com.nova.blog.exception.ResourceNotFoundException;
import com.nova.blog.mapper.UserMapper;
import com.nova.blog.model.User;
import com.nova.blog.payload.UserDTO;
import com.nova.blog.repository.UserRepository;
import com.nova.blog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistException("A user with this email already exists " + userDTO.getEmail());
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User newUser = userRepository.save(UserMapper.toModel(userDTO));
        return UserMapper.toDTO(newUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        Optional<User> existingUserWithEmail = userRepository.findByEmail(userDTO.getEmail());

        if(existingUserWithEmail.isPresent() && (existingUserWithEmail.get().getId() != userId)) {
            throw new EmailAlreadyExistException("A user with this email already exists " + userDTO.getEmail());
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user.setPassword(userDTO.getPassword());
        // Check if password needs to be updated
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(encodedPassword);
        }

        user.setAbout(userDTO.getAbout());

        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public UserDTO getUserByID(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOS = users.stream().map(user -> UserMapper.toDTO(user)).toList();
        return userDTOS;
    }

    @Override
    public void deleteUserById(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        userRepository.delete(user);
    }
}
