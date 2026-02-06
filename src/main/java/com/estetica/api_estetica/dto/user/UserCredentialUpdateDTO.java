package com.estetica.api_estetica.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialUpdateDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
