package budget;

public class Product {

    private final String name;
    private final double price;
    private final PRODUCT_TYPE type;

    public Product(String name, double price, PRODUCT_TYPE type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public PRODUCT_TYPE getType() {
        return type;
    }
}
