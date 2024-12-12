package ua.com.peoplepulse.productcatalog;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final Long id;

    public ProductNotFoundException(Long id) {
        this.id = id;
    }
}

