package service;

import entity.Product;
import factory.ProductEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.MemoryRepositoryByLambda;

import java.io.IOException;


public class ProductServiceTest {

    ProductEntityFactory productEntityFactory = new ProductEntityFactory();
    Product product = productEntityFactory.create();
    private final MemoryRepositoryByLambda<Product> memoryRepositoryByLambda = new MemoryRepositoryByLambda<>((Product) -> product.getId());


    @Test
    public void loadAllByMaxPriceTest() throws IOException {

        for (int i = 0; i < 10; i++) {
            product = productEntityFactory.create();
            product.setPrice(3);
            if (i % 2 == 0) {
                product.setPrice(1);
            }
            memoryRepositoryByLambda.save(product);
        }


        ProductService productService = new ProductService(memoryRepositoryByLambda);

        Assertions.assertEquals(5, productService.loadAllByMaxPrice(2).size());

    }

}
