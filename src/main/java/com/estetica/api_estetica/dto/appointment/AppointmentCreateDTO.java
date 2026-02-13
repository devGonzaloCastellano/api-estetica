package com.estetica.api_estetica.dto.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentCreateDTO {

    @NotNull
    private Long clientId;
    @NotNull
    private Long employeeId;
    @NotNull
    private Long treatmentId;
    @NotNull
    private LocalDateTime startTime;
}
