package ua.com.peoplepulse.productcatalog;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import ua.com.peoplepulse.productcatalog.model.Product;
import ua.com.peoplepulse.productcatalog.servises.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class ProductcatalogApplication {

	private final ProductService productServices;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			for(int i = 0; i < 10; i++) {
				Product product = new Product();
				product.setName("Product " + (i+1));
				product.setDescription("This is description of "+i+" product");
				product.setPrice(new BigDecimal(100 - i*2));
				product.setCategory(String.valueOf(i % 2));
				product.setCreated(LocalDateTime.now());
				productServices.createProduct(product);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogApplication.class, args);
	}

}
