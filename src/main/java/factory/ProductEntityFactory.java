package factory;

import entity.Product;

public class ProductEntityFactory extends SimpleEntityFactory<Product> {


    @Override
    public Product create() {
        Product product = new Product();
        product.setId(getNextId());
        return product;
        //return new Product(getNextId());
    }
}
