package com.example.spring_boot.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialsDTO {
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only letters")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
