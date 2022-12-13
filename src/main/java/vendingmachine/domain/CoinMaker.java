package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinMaker {

    private final Map<Coin, Integer> coins;

    public CoinMaker() {
        this.coins = initCoins();
    }

    public Map<Coin, Integer> makeCoins(int amount) {
        issueCoins(amount);
        return coins;
    }

    private Map<Coin, Integer> initCoins() {
        return Arrays.stream(Coin.values())
                .collect(Collectors.toMap(
                        key -> key,
                        value -> 0
                ));
    }

    private void issueCoins(int amount) {
        while (amount == 0) {
            Coin coin = getRandomCoin(amount);
            coins.put(coin, coins.get(coin) + 1);
            amount -= coin.getAmount();
        }
    }

    private Coin getRandomCoin(int holdingAmount) {
        int coinPrice = Randoms.pickNumberInList(Coin.getAvailableCoins(holdingAmount));
        return Coin.getCoin(coinPrice);
    }
}
