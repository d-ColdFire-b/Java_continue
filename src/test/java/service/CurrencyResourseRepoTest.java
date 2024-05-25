package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import repository.CurrencyResourceRepository;

public class CurrencyResourseRepoTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void ConstructorTest() throws JsonProcessingException {

        CurrencyResourceRepository currencyResourceRepository = new CurrencyResourceRepository(mapper);

    }
}
