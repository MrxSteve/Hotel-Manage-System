package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UpdateUserRequest {
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String picture;

    @Size(max = 10, message = "Pin must be at most 10 characters long")
    private String pin;
}
