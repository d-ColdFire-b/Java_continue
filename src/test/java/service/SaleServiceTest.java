package service;


import entity.Person;
import entity.Sale;
import factory.SaleEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.MemoryRepositoryByLambda;

import java.io.IOException;
import java.time.LocalDateTime;

public class SaleServiceTest {

    SaleEntityFactory saleEntityFactory = new SaleEntityFactory();

    Sale sale = saleEntityFactory.create();
    private final MemoryRepositoryByLambda<Sale> memoryRepositoryByLambda = new MemoryRepositoryByLambda<>((Sale) -> sale.getId());

    @Test
    public void loadAllByPersonId() throws IOException {

        for (int i = 0; i < 10; i++) {
            sale = saleEntityFactory.create();
            sale.setPerson(new Person(i));
            sale.setTimestamp(LocalDateTime.now());
            sale.setAmount(Math.random());
            if (i % 2 == 0) {
                sale.setPerson(new Person(1));
            }
            memoryRepositoryByLambda.save(sale);
        }

        SaleService saleService = new SaleService(memoryRepositoryByLambda);

        Assertions.assertEquals(6,saleService.loadAllByPersonId(1).size());

    }

}
