package factory;

import entity.Sale;

public class SaleEntityFactory extends SimpleEntityFactory<Sale>{
    @Override
    public Sale create() {
        return new Sale(getNextId());
    }
}
