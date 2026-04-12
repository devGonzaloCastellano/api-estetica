package com.estetica.api_estetica.controller;

import com.estetica.api_estetica.dto.treatment.TreatmentCreateDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentResponseDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentUpdateDTO;
import com.estetica.api_estetica.service.ITreatmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private ITreatmentService treatmentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'CLIENT')")
    public ResponseEntity<List<TreatmentResponseDTO>> getTreatment(){
        return ResponseEntity.ok(treatmentService.getTreatment());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TreatmentResponseDTO> createTreatment(@Valid @RequestBody TreatmentCreateDTO dto){

        TreatmentResponseDTO treatmentCreado = treatmentService.createTreatment(dto);
        return ResponseEntity
                .created(URI.create("/api/treatments/" + treatmentCreado.getId()))
                .body(treatmentCreado);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TreatmentResponseDTO> updateTreatment(@PathVariable Long id, @RequestBody TreatmentUpdateDTO dto){
        return ResponseEntity.ok(treatmentService.updateTreatment(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id){
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}
