package com.estetica.api_estetica.dto.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private Long id;
    private Long clientId;
    private Long employeeId;
    private Long treatmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String appointmentStatus;

}
