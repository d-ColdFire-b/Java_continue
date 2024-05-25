package repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Currency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class CurrencyResourceRepository extends MemoryRepository<Currency> {


    File dir = new File("data");

    public CurrencyResourceRepository(ObjectMapper mapper) throws JsonProcessingException {
        if (!this.dir.isDirectory()) {
            try {
                if (!this.dir.mkdir()) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        File file = new File(dir.getPath() + "/" + "currencies.json");
        String json_currencies;
        try {
            try (FileInputStream stream = new FileInputStream(file)) {
                try (Scanner scanner = new Scanner(stream)) {
                    json_currencies = scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("No 'currencies.json' file in directory!");
            throw new RuntimeException();
        }

        JsonNode node = mapper.readValue(json_currencies, JsonNode.class);


        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode currencies = it.next();
            int id = currencies.get("id").asInt();
            Currency currency = mapper.readValue(currencies.toString(), Currency.class);

            storage.put(id, currency);
        }

//        node.elements().forEachRemaining(x -> storage.put(x.get("id"), mapper.readValue(x.asText(), Currency.class))); // mapper.readValue demanding separate "catch" for some reason =(

    }

    @Override
    public void save(Currency obj) throws IOException {
        throw new RuntimeException("I will always do that");
    }

    @Override
    int getId(Currency obj) {
        for (Integer i : storage.keySet()) {
            if (storage.get(i).equals(obj))
            {
                return i;
            }
        }
        return -1;
    }
}
