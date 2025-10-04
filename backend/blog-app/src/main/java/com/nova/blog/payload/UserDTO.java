package com.nova.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotBlank
    @Size(min = 3, message = "Username must be min of 3 characters!")
    private String name;

    @NotBlank
    @Email(message = "Email address is not valid!")
    private String email;

    @NotBlank
    @Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters!")
    private String password;

    @NotBlank
    private String about;
}
