package ua.com.peoplepulse.productcatalog.convert;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import ua.com.peoplepulse.productcatalog.controller.dto.ProductResponse;
import ua.com.peoplepulse.productcatalog.model.Product;

@Component
public class ProductToProductResponseConverter implements Converter<Product, ProductResponse> {

    @Override
    public ProductResponse  convert (Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
