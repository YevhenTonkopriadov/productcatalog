package ua.com.peoplepulse.productcatalog.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {

    @NotEmpty(message = "name is required")
    private String name;
    private String description;

    @NotNull(message = "price is required")
    private BigDecimal price;
    private String category;
    private Integer stock;
}
