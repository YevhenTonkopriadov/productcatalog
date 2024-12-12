package ua.com.peoplepulse.productcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.com.peoplepulse.productcatalog.controller.dto.CreateProductRequest;
import ua.com.peoplepulse.productcatalog.controller.dto.ProductResponse;
import ua.com.peoplepulse.productcatalog.controller.dto.UpdateProductRequest;
import ua.com.peoplepulse.productcatalog.facade.ProductFacade;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.servises.ProductService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@ResponseBody
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductService productServices;

    @GetMapping
    public List<ProductResponse> findAllProducts() {
        return productFacade.findAll();
    }
    @PostMapping
    public Product createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productFacade.createProduct(createProductRequest);
    };

    @GetMapping (path = "/{id}")
    public ProductResponse findProductById (@PathVariable Long id) {
        return productFacade.findById(id);
    };

    @PutMapping (path ="/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return productFacade.updateProduct(id,updateProductRequest);
    }

    @DeleteMapping (path = "/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productServices.deleteById(id);
    }

    @GetMapping (path = "/category/{category}")
    public List <ProductResponse>  findAllProductsByCategory (@PathVariable String category) {
        return productFacade.findByCategory(category);
    }
}
