package ua.com.peoplepulse.productcatalog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.peoplepulse.productcatalog.model.Product;

@Repository
public interface ProductRepositories extends CrudRepository<Product, Long> {

    @Query("select c from Product c where c.category = ?1")
    Iterable<Product> findAllByCategory(String category);
}
