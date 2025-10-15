package com.nova.blog.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String user;
    private String password;
}
