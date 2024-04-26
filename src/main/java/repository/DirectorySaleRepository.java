package repository;

import entity.Person;
import entity.Sale;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DirectorySaleRepository implements Repository<Sale> {

    File dir = new File("sales");


    public DirectorySaleRepository() {
        if (!this.dir.isDirectory()) {
            try {
                if (!this.dir.mkdir()) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public DirectorySaleRepository(File dir) {
        this.dir = dir;
        if (!dir.isDirectory()) {
            try {
                if (!dir.mkdir()) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void save(Sale sale) throws IOException {
        File dirId = new File(dir.getPath() + "/" + sale.getId());
        try (FileOutputStream stream = new FileOutputStream(dirId)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(sale.getId());
                writer.println(sale.getTimestamp());
                writer.println(sale.getAmount());
                Person person = sale.getPerson();
                writer.println(person.getId());
                writer.println(person.getName());
                writer.println(person.getSurname());
                writer.println(person.getGender());
            }
        }
    }

    @Override
    public Sale load(int id) throws IOException {
        File dirId = new File(dir.getPath() + "/" + id);
        try {
            try (FileInputStream stream = new FileInputStream(dirId)) {
                try (Scanner scanner = new Scanner(stream)) {
                    Sale sale = new Sale();
                    sale.setId(scanner.nextInt());
                    scanner.nextLine();
                    sale.setTimestamp(LocalDateTime.parse(scanner.nextLine()));
                    sale.setAmount(scanner.nextDouble());
                    scanner.nextLine();
                    int personId = scanner.nextInt();
                    Repository<Person> personRepository = new DirectoryPersonRepository();
                    try {
                        sale.setPerson(personRepository.load(personId));
                    } catch (Exception e) {
                        System.out.println("Not saved in Persons =(");
                        Person person = new Person(personId);
                        scanner.nextLine();
                        person.setName(scanner.nextLine());
                        person.setSurname(scanner.nextLine());
                        person.setGender(scanner.nextLine());
                        sale.setPerson(person);
                        personRepository.save(person); // Not anymore >=)
                    }
                    return sale;
                }
            }
        } catch (Exception e) {
            System.out.println("No saves in repo for sale Id - " + id);
        }

        return null;
    }

    @Override
    public List<Sale> load(List<Integer> ids) throws IOException {

        List<Sale> saleList = new ArrayList<>();

        for (Integer id : ids) {
            saleList.add(load(id));
        }

        return saleList;

    }




    private List<Integer> getAllIds() {
        File[] files = dir.listFiles();
        List<Integer> fileIds = new ArrayList<>();
        for (File file : Objects.requireNonNull(files)) { // Objects.requireNonNull - IDEA suggestion
            fileIds.add(Integer.parseInt(file.getName()));
        }
        return fileIds;
    }

    @Override
    public List<Sale> loadAll() throws IOException {
        return load(getAllIds());
    }
}
