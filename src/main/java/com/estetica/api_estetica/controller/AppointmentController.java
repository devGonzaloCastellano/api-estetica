package com.estetica.api_estetica.controller;

import com.estetica.api_estetica.dto.AppointmentDTO;
import com.estetica.api_estetica.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAppointments(){
        return ResponseEntity.ok(appointmentService.getAppointment());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointment){
        AppointmentDTO appointmentCreado = appointmentService.createAppointment(appointment);
        return ResponseEntity
                .created(URI.create("/api/appointments/" + appointmentCreado.getId()))
                .body(appointmentCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id,@RequestBody AppointmentDTO appointment){
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
