package com.estetica.api_estetica.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDTO {

    private Long id;
    private String name;
    private Integer duration;
    private String description;
    private Double price;
}
