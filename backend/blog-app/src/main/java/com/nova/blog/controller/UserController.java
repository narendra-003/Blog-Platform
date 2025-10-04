package com.nova.blog.controller;

import com.nova.blog.payload.ApiResponse;
import com.nova.blog.payload.UserDTO;
import com.nova.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST - Create User
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO userResponseDTO = userService.createUser(userDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    // PUT - Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserDTO userDTO) {

        UserDTO userResponseDTO = userService.updateUser(userDTO, id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    // DELETE - Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {

        userService.deleteUserById(id);
        return ResponseEntity.ok().body(new ApiResponse("User deleted successfully.", true));
    }

    // GET - Get All Users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok().body(userDTOS);
    }

    // GET - Get Single User
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable int id) {

        UserDTO userDTO = userService.getUserByID(id);
        return ResponseEntity.ok().body(userDTO);
    }
}
