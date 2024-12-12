package ua.com.peoplepulse.productcatalog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "user name is required")
    private String name;
    private String description;

    @NotNull(message = "user age is required")
    private BigDecimal price;
    private String category;
    private Integer Stock;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
}
