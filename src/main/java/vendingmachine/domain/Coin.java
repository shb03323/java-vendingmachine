package vendingmachine.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static List<Integer> getAvailableCoins(int holdingAmount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> holdingAmount >= coin.amount)
                .map(coin -> coin.amount)
                .collect(Collectors.toList());
    }

    public static Coin getCoin(int coinPrice) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.amount == coinPrice)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 금액의 동전을 찾을 수 없습니다."));
    }

    public static Map<Integer, Integer> getChanges(int money) {
        Map<Integer, Integer> changes = new LinkedHashMap<>();
        for (Coin coin: Coin.values()) {
            int count = 0;
            while (money >= coin.amount) {
                count++;
                money -= coin.amount;
            }
            if (count > 0) {
                changes.put(coin.amount, count);
            }
        }
        return changes;
    }
}
