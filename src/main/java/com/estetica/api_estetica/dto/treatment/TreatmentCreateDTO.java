package com.estetica.api_estetica.dto.treatment;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentCreateDTO {

    @NotNull
    private String name;
    @NotNull
    private Integer duration;
    @NotNull
    private String description;
    @NotNull
    private Double price;
}
