package com.hoangtm14.spring.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    @NotEmpty(message = "E_NOT_EMPTY")
    private String name;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String description;
    @NotEmpty(message = "E_NOT_EMPTY")
    private BigDecimal price;
}
