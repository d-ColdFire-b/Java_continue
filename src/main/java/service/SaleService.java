package service;

import entity.Currency;
import entity.Sale;
import repository.Repository;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleService {

    Repository<Sale> repository;

    CurrencyService currencyService;

    public SaleService(Repository<Sale> repository, CurrencyService currencyService){
        this.repository = repository;
        this.currencyService = currencyService;
    }

    public double getAmount(Sale sale, Currency currency){

        Map<Currency, Double> map = sale.getAmount();

        double sum = 0;

        for (Currency key : map.keySet()) {
            double cost = map.get(key) ;
            cost *= currencyService.getRate(key,currency);
            sum +=cost;
        }

        return sum;
    }

    public List<Sale> loadAllByPersonId(int id) throws IOException {

        List<Sale> personsSales = repository.loadAll();

        return personsSales.stream()
                .filter(x -> x.getPerson().getId() == id)
                .collect(Collectors.toList());
    }



}
