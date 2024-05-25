package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sale implements Iterable<Product> {

    int id;

    Person person;

    LocalDateTime timestamp;
    public Map<Product, Double> products = new TreeMap<>(); // To delete public mod

    public Sale(int id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Sale(int id) {
        this.id = id;
    }

    public Sale() {
    }

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

    public Map<Currency, Double> getAmount() {

        Map<Currency, Double> amount = new HashMap<>();
        ArrayList<Currency> currencies = new ArrayList<>();
        iterator().forEachRemaining(x -> currencies.add(x.getCurrency()));

        for (Currency currency : currencies) {
            double sum = 0;
            for (Product product : this) {
                if (!product.currency.equals(currency)) {
                    continue;
                }
                sum += products.get(product);

            }
            amount.put(currency,sum);

        }

        return amount;
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
        return "Sale id - " + this.id + "| Sale Amount - " + getAmount() + "| Sale timestamp - " + formatter.format(this.timestamp) + "| Sale Person id - " + this.person.getId();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
