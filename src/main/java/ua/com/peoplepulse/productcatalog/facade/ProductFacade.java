package ua.com.peoplepulse.productcatalog.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.peoplepulse.productcatalog.ProductNotFoundException;
import ua.com.peoplepulse.productcatalog.controller.dto.CreateProductRequest;
import ua.com.peoplepulse.productcatalog.controller.dto.ProductResponse;
import ua.com.peoplepulse.productcatalog.controller.dto.UpdateProductRequest;
import ua.com.peoplepulse.productcatalog.convert.ProductToProductResponseConverter;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.servises.ProductService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final ProductToProductResponseConverter productToProductResponseConverter;

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product createProduct = new Product();
        createProduct.setName(createProductRequest.getName());
        createProduct.setDescription(createProductRequest.getDescription());
        createProduct.setPrice(createProductRequest.getPrice());
        createProduct.setCategory(createProductRequest.getCategory());
        createProduct.setLastUpdated(LocalDateTime.now());
        createProduct.setCreated(LocalDateTime.now());
        createProduct.setStock(createProductRequest.getStock());
        return productToProductResponseConverter.convert(productService.createProduct(createProduct));
    }

    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        try {
            Product updateProduct = productService.findById(id).get();
            updateProduct.setName(updateProductRequest.getName());
            updateProduct.setDescription(updateProductRequest.getDescription());
            updateProduct.setPrice(updateProductRequest.getPrice());
            updateProduct.setCategory(updateProductRequest.getCategory());
            updateProduct.setLastUpdated(LocalDateTime.now());
            updateProduct.setStock(updateProductRequest.getStock());
            return productToProductResponseConverter.convert(productService.updateProduct(updateProduct));
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }


    public List<ProductResponse> findAll() {
        Iterable<Product> iterableProducts = productService.findAll();
        return StreamSupport.stream(iterableProducts.spliterator(), false)
                .map(productToProductResponseConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long productId) {
        try {
            Product product = productService.findById(productId).get();
            return productToProductResponseConverter.convert(product);
        } catch (Exception e) {
            throw new ProductNotFoundException(productId);
        }
    }

    public List<ProductResponse> findByCategory(String category) {
        Iterable<Product> iterableProducts = productService.findByCategory(category);
        return StreamSupport.stream(iterableProducts.spliterator(), false)
                .map(productToProductResponseConverter::convert)
                .collect(Collectors.toList());
    }
}
