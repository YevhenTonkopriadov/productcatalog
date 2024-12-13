package ua.com.peoplepulse.productcatalog.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.peoplepulse.productcatalog.ProductNotFoundException;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.repositories.ProductRepositori;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepositori productRepositories;

    @Cacheable(value = "allProducts")
    public Iterable<Product> findAll() {
        log.info("Service: Fetching all products");
        return productRepositories.findAll();
    }

    @Cacheable(value = "singleProduct", key = "#productId")
    public Optional<Product> findById(Long productId) {
        log.info("Service: Fetching product with id {}", productId);
        return productRepositories.findById(productId);
    }

    @Caching(evict = {
            @CacheEvict(value = "productByCategory", allEntries = true),
            @CacheEvict(value = "allProducts", allEntries = true)
    })
    public Product createProduct(Product product) {
        Product savedProduct = productRepositories.save(product);
        log.info("Service: Saving product with name {}", savedProduct.getId());
        return savedProduct;
    }

    @Caching(evict = {
            @CacheEvict(value = "productByCategory", allEntries = true),
            @CacheEvict(value = "allProducts", allEntries = true)
    })
    public Product updateProduct(Product product) {
        Product updatedProduct = productRepositories.findById(product.getId()).get();
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setLastUpdated(LocalDateTime.now());
        log.info("Service: Saving product with name {}", updatedProduct.getId());
        return productRepositories.save(updatedProduct);
    }

    @Caching(evict = {
            @CacheEvict(value = "productByCategory", allEntries = true),
            @CacheEvict(value = "allProducts", allEntries = true)
    })
    public void deleteById(Long id) {
        try {
            Product product = productRepositories.findById(id).get();
            productRepositories.deleteById(id);
            log.info("Service: Deleting product with id {}", id);
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Cacheable(value = "singleProduct", key = "#productId")
    public boolean existsById(Long productId) {
        return productRepositories.existsById(productId);
    }

    ;

    @Cacheable(value = "productByCategory", key = "#category")
    public Iterable<Product> findByCategory(String category) {
        log.info("Service: Finding products by category {}", category);
        return productRepositories.findAllByCategory(category);
    }
}
