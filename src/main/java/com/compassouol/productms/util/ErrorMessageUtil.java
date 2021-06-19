package com.compassouol.productms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessageUtil {

    private Integer statusCode;
    private String message;

}
