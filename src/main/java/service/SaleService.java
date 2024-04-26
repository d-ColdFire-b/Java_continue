package service;

import entity.Sale;
import repository.DirectorySaleRepository;
import repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SaleService {

    Repository<Sale> repository;


    public SaleService(Repository<Sale> repository){
        this.repository = repository;
    }

    public List<Sale> loadAllByPersonId(int id) throws IOException {

        List<Sale> personsSales = repository.loadAll();

        return personsSales.stream()
                .filter(x -> x.getPerson().getId() == id)
                .collect(Collectors.toList());
    }

}
