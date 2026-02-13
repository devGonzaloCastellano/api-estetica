package com.estetica.api_estetica.dto.appointment;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private String clientName;
    private String employeeName;
    private String treatmentName;
    private String treatmentDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}
