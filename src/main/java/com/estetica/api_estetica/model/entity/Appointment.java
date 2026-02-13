package com.estetica.api_estetica.model.entity;

import com.estetica.api_estetica.model.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @ManyToOne
    @JoinColumn(name = "treatment_id", nullable = false)

    private Treatment treatment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;


    //Recalcula el tiempo de finalización de los turnos según la duración del tratamiento
    public void recalculateEndTime() {
        if (this.startTime != null && this.treatment != null) {
            this.endTime = this.startTime.plusMinutes(this.treatment.getDuration());
        }
    }

}
