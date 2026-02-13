package com.estetica.api_estetica.dto.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AppointmentUpdateDTO {
    private Long treatmentId;
    private LocalDateTime startTime;
}
