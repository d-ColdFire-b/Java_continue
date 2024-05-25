package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person;
import entity.Sale;
import factory.SaleEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.CurrencyResourceRepository;
import repository.MemoryRepositoryByLambda;

import java.io.IOException;
import java.time.LocalDateTime;

public class SaleServiceTest {

    SaleEntityFactory saleEntityFactory = new SaleEntityFactory();

    ObjectMapper mapper = new ObjectMapper();


    Sale sale = saleEntityFactory.create();
    private final MemoryRepositoryByLambda<Sale> memoryRepositoryByLambda = new MemoryRepositoryByLambda<>((Sale) -> sale.getId());

    @Test
    public void loadAllByPersonId() throws IOException {

        CurrencyResourceRepository currencyResourceRepository = new CurrencyResourceRepository(mapper);

        CurrencyService currencyService = new CurrencyService(mapper, currencyResourceRepository);

        for (int i = 0; i < 10; i++) {
            sale = saleEntityFactory.create();
            sale.setPerson(new Person(i));
            sale.setTimestamp(LocalDateTime.now());
//            sale.setAmount(Math.random());
            if (i % 2 == 0) {
                sale.setPerson(new Person(1));
            }
            memoryRepositoryByLambda.save(sale);
        }

        SaleService saleService = new SaleService(memoryRepositoryByLambda, currencyService);

        Assertions.assertEquals(6,saleService.loadAllByPersonId(1).size());

    }

}
