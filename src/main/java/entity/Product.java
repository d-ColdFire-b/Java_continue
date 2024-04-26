package entity;

public class Product implements Comparable<Product> {

    int id;
    String name;
    double price;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product product) {
        return Integer.compare(this.id, product.id);

    }
}
