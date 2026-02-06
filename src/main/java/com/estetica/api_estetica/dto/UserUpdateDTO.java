package com.estetica.api_estetica.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {

    private String firstName;
    private String lastName;
    @Email
    private String email;
}
