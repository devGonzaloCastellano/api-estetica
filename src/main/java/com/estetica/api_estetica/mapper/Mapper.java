package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.treatment.TreatmentDTO;
import com.estetica.api_estetica.model.entity.Treatment;


public class Mapper {


    public static TreatmentDTO TreatToDTO(Treatment t){
        if(t == null) return null;

        return TreatmentDTO.builder()
                .id(t.getId())
                .name(t.getName())
                .duration(t.getDuration())
                .description(t.getDescription())
                .price(t.getPrice())
                .build();
    }


}
