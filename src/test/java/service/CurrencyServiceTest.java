package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import repository.CurrencyResourceRepository;

import java.io.IOException;

public class CurrencyServiceTest {

    ObjectMapper mapper = new ObjectMapper();





    @Test
    public void IteratorCheck() throws IOException {

        CurrencyResourceRepository currencyResourceRepository = new CurrencyResourceRepository(mapper);

        CurrencyService currencyService = new CurrencyService(mapper, currencyResourceRepository);



    }


}
