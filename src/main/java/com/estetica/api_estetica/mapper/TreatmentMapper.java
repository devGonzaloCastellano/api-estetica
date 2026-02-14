package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.treatment.TreatmentCreateDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentResponseDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentUpdateDTO;
import com.estetica.api_estetica.model.entity.Treatment;

public class TreatmentMapper {

    /**
     * Transforma una entidad Treatment en un TreatmentResponseDTO.
     *
     * @param treatment entidad Treatment a convertir
     * @return DTO de respuesta con los datos públicos de los tratamientos
     */
    public static TreatmentResponseDTO toResponse(Treatment treatment){
        if (treatment == null) return null;

        return TreatmentResponseDTO.builder()
                .id(treatment.getId())
                .name(treatment.getName())
                .duration(treatment.getDuration())
                .description(treatment.getDescription())
                .price(treatment.getPrice())
                .build();
    }


    /**
     * Crea una entidad Treatment a partir de un TreatmentCreateDTO
     *
     * @param dto datos recibidos para la creación de un tratamiento
     * @return nueva instancia de Treatment con los datos inicializados.
     */
    public static Treatment fromCreate(TreatmentCreateDTO dto){
        if (dto == null) return null;

        return Treatment.builder()
                .name(dto.getName())
                .duration(dto.getDuration())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }


    /**
     * Aplica la actualización de datos de un tratamiento ya existente
     *
     * @param treatment entidad treatment a modificar
     * @param dto datos provenientes del request
     */
    public static void updateEntity(Treatment treatment, TreatmentUpdateDTO dto){
        if (dto.getName() != null){
            treatment.setName(dto.getName());
        }
        if (dto.getDuration() != null){
            treatment.setDuration(dto.getDuration());
        }
        if (dto.getDescription() != null){
            treatment.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null){
            treatment.setPrice(dto.getPrice());
        }

    }
}
