package ua.com.peoplepulse.productcatalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.servises.ProductServises;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@ResponseBody
@Slf4j
public class ControllerAPI {

    private final ProductServises productServices;

    @GetMapping
    public Iterable <Product>  findAllProducts() {
        return productServices.findAll();
    }
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productServices.createProduct(product);
    };

    @GetMapping (path = "/{id}")
    public Product findProductById (@PathVariable String id) {
        try{
            Long productId = Long.parseLong(id);
            if (productServices.findById(productId).isPresent())
                return productServices.findById(productId).get();
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
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        try {
            Long productId = Long.parseLong(id);
            if (productServices.findById(productId).isPresent()) {
                Product updatedProduct = productServices.findById(productId).get();
                updatedProduct.setName(product.getName());
                updatedProduct.setDescription(product.getDescription());
                updatedProduct.setPrice(product.getPrice());
                updatedProduct.setCategory(product.getCategory());
                updatedProduct.setLastUpdated(LocalDateTime.now());
                return productServices.updateProduct(updatedProduct);
            } else {
                log.info("Controller:Product {} can't updating", id);
                return null;
            }
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
    public Iterable <Product>  findAllProductsByCategory (@PathVariable String category) {
        return productServices.findByCategory(category);
    }
}
