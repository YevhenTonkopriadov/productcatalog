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

    @NotEmpty(message = "name is required")
    private String name;
    private String description;

    @NotNull(message = "price is required")
    private BigDecimal price;
    private String category;
    private Integer stock;
    private LocalDateTime created;

    @Column(name = "LASTUPDATED")
    private LocalDateTime lastUpdated;
}
