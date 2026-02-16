package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.treatment.TreatmentCreateDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentResponseDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentUpdateDTO;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.TreatmentMapper;
import com.estetica.api_estetica.model.entity.Treatment;
import com.estetica.api_estetica.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService implements ITreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Override
    public List<TreatmentResponseDTO> getTreatment() {
        return treatmentRepository.findAll().stream()
                .map(TreatmentMapper::toResponse)
                .toList();
    }

    @Override
    public TreatmentResponseDTO createTreatment(TreatmentCreateDTO dto) {
        Treatment treatment = TreatmentMapper.fromCreate(dto);
        return TreatmentMapper.toResponse(treatmentRepository.save(treatment));
    }

    @Override
    public TreatmentResponseDTO updateTreatment(Long id, TreatmentUpdateDTO dto) {

        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));
        TreatmentMapper.updateEntity(treatment, dto);
        return TreatmentMapper.toResponse(treatmentRepository.save(treatment));
    }

    @Override
    public void deleteTreatment(Long id) {
        if (!treatmentRepository.existsById(id))
            throw new NotFoundException("Tratamiento no encontrado para eliminar");
        treatmentRepository.deleteById(id);
    }
}
