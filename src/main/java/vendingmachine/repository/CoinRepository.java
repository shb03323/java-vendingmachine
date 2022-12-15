package vendingmachine.repository;

import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinMaker;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoinRepository {

    private final Map<Coin, Integer> coins;

    public CoinRepository(int inputAmount) {
        CoinMaker coinMaker = new CoinMaker();
        this.coins = coinMaker.makeCoins(inputAmount);
    }

    public Map<Integer, Integer> getCoins() {
        Map<Integer, Integer> coinsToInteger = new LinkedHashMap<>();
        for (Coin coin: coins.keySet()) {
            coinsToInteger.put(coin.getAmount(), coins.get(coin));
        }
        return coinsToInteger;
    }

    public Map<Integer, Integer> getChanges(int insertedMoney) {
        return Coin.getChanges(insertedMoney);
    }
}
