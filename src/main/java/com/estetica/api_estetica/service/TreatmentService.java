package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.treatment.TreatmentDTO;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.Mapper;
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
    public List<TreatmentDTO> getTreatment() {
        return treatmentRepository.findAll().stream().map(Mapper::TreatToDTO).toList();
    }

    @Override
    public TreatmentDTO createTreatment(TreatmentDTO treatmentDto) {

        Treatment treatment = Treatment.builder()
                .name(treatmentDto.getName())
                .duration(treatmentDto.getDuration())
                .description(treatmentDto.getDescription())
                .price(treatmentDto.getPrice())
                .build();

        return Mapper.TreatToDTO(treatmentRepository.save(treatment));
    }

    @Override
    public TreatmentDTO updateTreatmen(Long id, TreatmentDTO treatmentDto) {

        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));

        treatment.setName(treatmentDto.getName());
        treatment.setDuration(treatmentDto.getDuration());
        treatment.setDescription(treatmentDto.getDescription());
        treatment.setPrice(treatmentDto.getPrice());

        return Mapper.TreatToDTO(treatmentRepository.save(treatment));
    }

    @Override
    public void deleteTreatment(Long id) {
        if (!treatmentRepository.existsById(id))
            throw new NotFoundException("Tratamiento no encontrado para eliminar");
    }
}
