package repository;

import entity.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DirectoryPersonRepository implements Repository<Person> {


    File dir = new File("Persons");


    public DirectoryPersonRepository() {
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

    public DirectoryPersonRepository(File dir) {
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
    public void save(Person person) throws IOException {
        File dirId = new File(dir.getPath() + "/" + person.getId());
        try (FileOutputStream stream = new FileOutputStream(dirId)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(person.getId());
                writer.println(person.getName());
                writer.println(person.getSurname());
                writer.println(person.getGender());
            }
        }
    }


    @Override
    public Person load(int id) {
        File dirId = new File(dir.getPath() + "/" + id);
        try {
            try (FileInputStream stream = new FileInputStream(dirId)) {
                try (Scanner scanner = new Scanner(stream)) {
                    Person person = new Person(scanner.nextInt());
                    scanner.nextLine();
                    person.setName(scanner.nextLine());
                    person.setSurname(scanner.nextLine());
                    person.setGender(scanner.nextLine());
                    return person;
                }
            }
        } catch (Exception e) {
            System.out.println("No saves in repo for person Id - " + id);
        }

        return null;
    }

    @Override
    public List<Person> load(List<Integer> ids) {

        List<Person> personList = new ArrayList<>();

        for (Integer id : ids) {
            personList.add(load(id));
        }

        return personList;
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
    public List<Person> loadAll() {
        return load(getAllIds());
    }
}
