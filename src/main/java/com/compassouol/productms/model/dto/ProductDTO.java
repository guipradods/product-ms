package com.compassouol.productms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Positive
    @NotNull
    private Double price;

}
