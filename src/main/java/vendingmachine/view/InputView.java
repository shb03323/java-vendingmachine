package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static int inputMachineHoldingAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return getPrice();
    }

    public static String inputProducts() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = Console.readLine();
        InputValidator.validateProductsInput(input);
        OutputView.printBlankLine();
        return input;
    }

    public static int inputMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        return getPrice();
    }

    private static int getPrice() {
        String input = Console.readLine();
        int amount = InputValidator.changeToNumber(input);
        InputValidator.validatePrice(amount);
        OutputView.printBlankLine();
        return amount;
    }
}
