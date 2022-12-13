package vendingmachine.view;

import java.util.Map;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printBlankLine() {
        System.out.println();
    }

    public static void printCoinsOfVendingMachine(Map<Integer, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        for (int coin : coins.keySet()) {
            System.out.println(coin + "원 - " + coins.get(coin) + "개");
        }
        printBlankLine();
    }

    public static void printMoneyLeft(int money) {
        System.out.println("투입 금액: " + money + "원");
    }
}
