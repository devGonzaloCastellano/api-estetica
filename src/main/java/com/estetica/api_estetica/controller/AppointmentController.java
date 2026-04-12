package com.estetica.api_estetica.controller;

import com.estetica.api_estetica.dto.appointment.AppointmentCreateDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentResponseDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentUpdateDTO;
import com.estetica.api_estetica.service.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointments(){
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentCreateDTO dto){
        AppointmentResponseDTO appointmentCreado = appointmentService.createAppointment(dto);
        return ResponseEntity
                .created(URI.create("/api/appointments/" + appointmentCreado.getId()))
                .body(appointmentCreado);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,@RequestBody AppointmentUpdateDTO dto){
        return ResponseEntity.ok(appointmentService.updateAppointment(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
