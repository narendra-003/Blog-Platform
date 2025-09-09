package com.nova.blog.service;

import com.nova.blog.payload.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getUserByID(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUserById(Integer userId);
}
