package entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sale implements Iterable<Product> {

    int id;
    double amount;
    Person person;

    LocalDateTime timestamp;

    public Sale(int id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Sale (int id){
        this.id = id;
    }

    public Sale() {
    }
    Map<Product, Double> products = new TreeMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return "Sale id - " + this.id + "| Sale Amount - " + this.amount + "| Sale timestamp - " + formatter.format(this.timestamp) + "| Sale Person id - " + this.person.getId();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
