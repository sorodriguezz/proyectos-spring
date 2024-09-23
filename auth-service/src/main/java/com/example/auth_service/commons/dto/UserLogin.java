package com.example.auth_service.commons.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
