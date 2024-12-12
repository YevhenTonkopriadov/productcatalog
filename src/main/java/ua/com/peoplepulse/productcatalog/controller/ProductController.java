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
    public ProductResponse findProductById (@PathVariable String id) {
        try{
            Long productId = Long.parseLong(id);
            if (productServices.findById(productId).isPresent())
                return productFacade.findById(productId);
            else {
                log.info("Controller:Product {} can't finding", id);
                return null;
            }
        } catch (NumberFormatException e) {
            log.info("Controller:Product {} can't finding", id);
            return null;
        }
    };

    @PutMapping (path ="/{id}")
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        try {
            Long productId = Long.parseLong(id);
            return productFacade.updateProduct(updateProductRequest);
        }
        catch (NumberFormatException e) {
            log.info("Controller:Product {} can't updating", id);
            return null;
        }
    }

    @DeleteMapping (path = "/{id}")
    public void deleteProductById(@PathVariable String id) {
        try{
            Long productId = Long.parseLong(id);
            if (productServices.findById(productId).isPresent())
                productServices.deleteById(productId);
            else log.info("Controller:Product {} can't delete", id);
        } catch (NumberFormatException e) {
            log.info("Controller:Product {} can't delete", id);
        }
    }

    @GetMapping (path = "/category/{category}")
    public List <ProductResponse>  findAllProductsByCategory (@PathVariable String category) {
        return productFacade.findByCategory(category);
    }
}
