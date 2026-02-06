package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.treatment.TreatmentDTO;

import java.util.List;

public interface ITreatmentService {

    List<TreatmentDTO> getTreatment();
    TreatmentDTO createTreatment(TreatmentDTO treatmentDto);
    TreatmentDTO updateTreatmen(Long id, TreatmentDTO treatmentDto);
    void deleteTreatment(Long id);
}
