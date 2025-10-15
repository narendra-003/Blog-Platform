package com.nova.blog.controller;

import com.nova.blog.payload.ApiResponse;
import com.nova.blog.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/users/{userID}/make-admin")
    public ResponseEntity<ApiResponse> makeUserAdmin(@PathVariable Integer userID) {
        adminService.promoteToAdmin(userID);

        return ResponseEntity.ok().body(new ApiResponse("User has been promoted to ADMIN.", true));
    }
}
