package vendingmachine.service;

import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;

import java.util.Map;

public class VendingMachine {

    private static CoinRepository coinRepository;
    private static ProductRepository productRepository;
    private int insertedMoney;

    public void setAmount(int inputAmount) {
        coinRepository = new CoinRepository(inputAmount);
    }

    public void setProducts(String inputProducts) {
        productRepository = new ProductRepository(inputProducts);
    }

    public void insertMoney(int money) {
        insertedMoney = money;
    }

    public Map<Integer, Integer> getCoins() {
        return coinRepository.getCoins();
    }

    public int getInsertedMoney() {
        return insertedMoney;
    }

    public boolean availableToPurchase() {
        return productRepository.availableToPurchase(insertedMoney);
    }

    public void purchaseProduct(String productName) {
        insertedMoney = productRepository.purchaseProductAndGetChanges(productName, insertedMoney);
    }

    public Map<Integer, Integer> getChanges() {
        return coinRepository.getChanges(insertedMoney);
    }
}
