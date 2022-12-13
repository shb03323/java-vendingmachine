package vendingmachine.domain;

public class Product {

    private final String name;
    private final int cost;
    private int count;

    public Product(String name, int cost, int count) {
        this.name = name;
        this.cost = cost;
        this.count = count;
    }
}
