package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinMaker;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductMaker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private static Map<Coin, Integer> coins;
    private static List<Product> products;
    private static int insertedMoney;

    private static VendingMachine instance;

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    private VendingMachine() {}

    public void setAmount(int inputAmount) {
        CoinMaker coinMaker = new CoinMaker();
        coins = coinMaker.makeCoins(inputAmount);
    }

    public void setProducts(String inputProducts) {
        ProductMaker productMaker = new ProductMaker();
        products = productMaker.makeProduct(inputProducts);
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
        return products.stream()
                .anyMatch(product -> product.canBuy(insertedMoney));
    }

    public void purchaseProduct(String productName) {
        Product product = findProduct(productName);
        checkCanPurchase(product);
        buy(product);
    }

    public Map<Integer, Integer> getChanges() {
        return Coin.getChanges(insertedMoney);
    }

    private Product findProduct(String productName) {
        return products.stream()
                .filter(product -> product.isSameName(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 상품명입니다."));
    }

    private void checkCanPurchase(Product product) {
        if (!product.canBuy(insertedMoney)) {
            throw new IllegalArgumentException("[ERROR] 보유 금액으로 해당 상품을 구매할 수 없습니다.");
        }
    }

    private void buy(Product product) {
        insertedMoney = product.purchaseAndGetRemainMoney(insertedMoney);
    }
}
