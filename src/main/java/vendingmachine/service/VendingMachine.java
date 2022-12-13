package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinMaker;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private static Map<Coin, Integer> coins;

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

    public Map<Integer, Integer> getCoins() {
        Map<Integer, Integer> coinsToInteger = new HashMap<>();
        for (Coin coin: coins.keySet()) {
            coinsToInteger.put(coin.getAmount(), coins.get(coin));
        }
        return coinsToInteger;
    }
}
