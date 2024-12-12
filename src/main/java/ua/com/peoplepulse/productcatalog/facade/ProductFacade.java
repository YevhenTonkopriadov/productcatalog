package ua.com.peoplepulse.productcatalog.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.peoplepulse.productcatalog.controller.dto.CreateProductRequest;
import ua.com.peoplepulse.productcatalog.controller.dto.ProductResponse;
import ua.com.peoplepulse.productcatalog.controller.dto.UpdateProductRequest;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.servises.ProductService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public Product createProduct (CreateProductRequest createProductRequest) {
        Product createProduct =new Product();
        createProduct.setName(createProductRequest.getName());
        createProduct.setDescription(createProductRequest.getDescription());
        createProduct.setPrice(createProductRequest.getPrice());
        createProduct.setCategory(createProductRequest.getCategory());
        createProduct.setLastUpdated(LocalDateTime.now());
        createProduct.setCreated(LocalDateTime.now());
        createProduct.setStock(createProductRequest.getStock());
        return productService.createProduct(createProduct);
    };

    public Product updateProduct(UpdateProductRequest updateProductRequest) {
        Product updateProduct = new Product();
        updateProduct.setName(updateProductRequest.getName());
        updateProduct.setDescription(updateProductRequest.getDescription());
        updateProduct.setPrice(updateProductRequest.getPrice());
        updateProduct.setCategory(updateProductRequest.getCategory());
        updateProduct.setLastUpdated(LocalDateTime.now());
        updateProduct.setStock(updateProductRequest.getStock());
        return productService.updateProduct(updateProduct);
    }


    public List<ProductResponse> findAll() {
        Iterable<Product> iterableProducts = productService.findAll();
        List<ProductResponse> productList = new ArrayList<>();
        for (Product product : iterableProducts) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategory());
            productList.add(productResponse);
        }
        return productList;
    }

    public ProductResponse findById(Long productId) {
        Product product= productService.findById(productId).get();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    public List<ProductResponse> findByCategory(String category) {
        Iterable<Product> iterableProducts = productService.findByCategory(category);
        List<ProductResponse> productList = new ArrayList<>();
        for (Product product : iterableProducts) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategory(product.getCategory());
        }
        return productList;
    }
}
