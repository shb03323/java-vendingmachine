package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinMaker;

import java.util.Map;

public class VendingMachine {

    private static int amount;
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
}