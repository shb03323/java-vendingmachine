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

    public boolean canBuy(int money) {
        return money >= cost && count > 0;
    }

    public boolean isSameName(String targetName) {
        return name.equals(targetName);
    }

    public int purchaseAndGetRemainMoney(int money) {
        count--;
        return money - cost;
    }
}
