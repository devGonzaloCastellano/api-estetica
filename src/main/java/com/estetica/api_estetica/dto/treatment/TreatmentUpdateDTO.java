package com.estetica.api_estetica.dto.treatment;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentUpdateDTO {
    private String name;
    private Integer duration;
    private String description;
    private Double price;
}
