package com.estetica.api_estetica.controller;

import com.estetica.api_estetica.dto.treatment.TreatmentDTO;
import com.estetica.api_estetica.service.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private ITreatmentService treatmentService;

    @GetMapping
    public ResponseEntity<List<TreatmentDTO>> getTreatment(){
        return ResponseEntity.ok(treatmentService.getTreatment());
    }

    @PostMapping
    public ResponseEntity<TreatmentDTO> createTreatment(@RequestBody TreatmentDTO treatment){

        TreatmentDTO treatmentCreado = treatmentService.createTreatment(treatment);
        return ResponseEntity
                .created(URI.create("/api/treatments/" + treatmentCreado.getId()))
                .body(treatmentCreado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentDTO> updateTreatment(@PathVariable Long id,@RequestBody TreatmentDTO treatment){
        return ResponseEntity.ok(treatmentService.updateTreatmen(id, treatment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatement(@PathVariable Long id){
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}
