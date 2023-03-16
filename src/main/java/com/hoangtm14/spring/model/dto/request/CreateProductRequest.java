package com.hoangtm14.spring.model.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotEmpty(message = "E_NOT_EMPTY")
    private String name;
    private String description;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String code;
    @NotNull(message = "E_NOT_EMPTY")
    @Positive(message = "E_POSITIVE_FIELD")
    private BigDecimal price;
    private MultipartFile thumbnail;
}
