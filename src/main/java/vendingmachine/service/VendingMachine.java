package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinMaker;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductMaker;
import vendingmachine.repository.ProductRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private static Map<Coin, Integer> coins;
    private static ProductRepository productRepository;
    private static int insertedMoney;

    private static VendingMachine instance;

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public void setAmount(int inputAmount) {
        CoinMaker coinMaker = new CoinMaker();
        coins = coinMaker.makeCoins(inputAmount);
    }

    public void setProducts(String inputProducts) {
        productRepository = new ProductRepository(inputProducts);
    }

    public Map<Integer, Integer> getCoins() {
        Map<Integer, Integer> coinsToInteger = new LinkedHashMap<>();
        for (Coin coin: coins.keySet()) {
            coinsToInteger.put(coin.getAmount(), coins.get(coin));
        }
        return coinsToInteger;
    }

    public void insertMoney(int money) {
        insertedMoney = money;
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
        return Coin.getChanges(insertedMoney);
    }
}
