package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.treatment.TreatmentCreateDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentResponseDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentUpdateDTO;

import java.util.List;

public interface ITreatmentService {

    List<TreatmentResponseDTO> getTreatment();
    TreatmentResponseDTO createTreatment(TreatmentCreateDTO dto);
    TreatmentResponseDTO updateTreatmen(Long id, TreatmentUpdateDTO dto);
    void deleteTreatment(Long id);
}
