package service;

import entity.Product;
import repository.DirectoryProductRepository;
import repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    Repository<Product> repository;

    public ProductService(Repository<Product> repository){
        this.repository = repository;
    }

    public List<Product> loadAllByMaxPrice (double maxPrice) throws IOException {

        List<Product> allProducts = repository.loadAll();

        return allProducts.stream()
                .filter(x -> x.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }

}
