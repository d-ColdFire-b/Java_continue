package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;
import repository.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CurrencyService {

    Repository<Currency> currencyRepository;
    Map<Currency, Map<Currency, Double>> rates = new HashMap<>();

    File dir = new File("data");

    CurrencyService(ObjectMapper mapper, Repository<Currency> currencyRepository) throws IOException {

        this.currencyRepository = currencyRepository;

        URL url = CurrencyService.class.getResource("/currencies_rates.json");

        JsonNode node = mapper.readValue(url, JsonNode.class);


        //Stopped on tsk 10

        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {

            Map.Entry<String, JsonNode> entry = it.next();

            Currency currency = currencyRepository.load(Integer.parseInt(entry.getKey()));

            Map<Currency, Double> rate = new HashMap<>();

            for (Iterator<Map.Entry<String, JsonNode>> it2 = entry.getValue().fields(); it2.hasNext(); ) {

                Map.Entry<String, JsonNode> entry2 = it2.next();

                Currency currency2 = currencyRepository.load(Integer.parseInt(entry2.getKey()));

                rate.put(currency2, entry2.getValue().asDouble());

            }

            rates.put(currency, rate);

        }

        System.out.println(rates);

    }

    public Currency getById(int id) throws IOException {

        return currencyRepository.load(id);
    }

    public Currency getById(String isoName) throws IOException {

        for (Currency currency : currencyRepository.loadAll()) {
            if (currency.getIsoName().equals(isoName)) {
                return currency;
            }
        }

        return null;
    }


    public double getRate (Currency from, Currency to){
        try {
            return rates.get(to).get(from);
        }
        catch (Exception exception){
            return -1;
        }
    }


}
