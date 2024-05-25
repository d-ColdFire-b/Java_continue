package service;

import entity.Currency;
import entity.Product;
import entity.Sale;
import factory.ProductEntityFactory;
import factory.SaleEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SaleTest {

    SaleEntityFactory saleEntityFactory = new SaleEntityFactory();

    ProductEntityFactory productEntityFactory = new ProductEntityFactory();

    Random r = new Random();
    @Test
    public void getAmountTest(){

        Sale sale = saleEntityFactory.create();


        for (int i = 0; i < 10; i++) {
            Currency currency = new Currency();
            Product product = productEntityFactory.create();
            double qty;
            if (i%2 == 1) {
                currency.setFullName("Piss");
                currency.setIsoName("pss");
                currency.setId(1);
                qty = 1;
                product.setPrice(1);
            } else {
                currency.setFullName("jobbie");
                currency.setIsoName("poo");
                currency.setId(2);
                qty = 2;
                product.setPrice(2);

            }

            product.setCurrency(currency);
            product.setName("product number " + i);

            sale.products.put(product,qty);
        }
//        sale.products.forEach((x,y)-> System.out.println(x.toString() + "\n" + y));



        Assertions.assertEquals("{Currency{id=2, fullName='jobbie', isoName='poo'}=10.0, Currency{id=1, fullName='Piss', isoName='pss'}=5.0}",
                                            sale.getAmount().toString());
    }

}
