package com.compassouol.productms.model.dto;

import com.compassouol.productms.messages.ErrorMessages;
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

    @NotNull(message = ErrorMessages.EMPTY_NAME)
    private String name;

    @NotNull(message = ErrorMessages.EMPTY_DESCRIPTION)
    private String description;

    @Positive(message = ErrorMessages.INVALID_PRICE)
    @NotNull(message = ErrorMessages.EMPTY_PRICE)
    private Double price;

}
