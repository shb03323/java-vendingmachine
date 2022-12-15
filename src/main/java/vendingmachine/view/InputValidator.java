package vendingmachine.view;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {

    private final static String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final static Integer MIN_COIN_PRICE = 10;

    public static int changeToNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "숫자만을 입력해주세요.");
        }
        return Integer.parseInt(input);
    }

    public static void validatePrice(int input) {
        if (input % MIN_COIN_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "10원으로 나누어 떨어지지 않는 금액입니다.");
        }
    }

    public static void validateProductsInput(String input) {
        List<String> products = List.of(input.split(";"));
        products.forEach(InputValidator::validateProductFormat);
    }

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static void validateProductFormat(String input) {
        validateProductFormatLength(input);
        char beginChar = input.charAt(0);
        char lastChar = input.charAt(input.length() - 1);
        validateBrackets(beginChar, lastChar);
        String exceptBrackets = input.substring(1, input.length() - 1);
        List<String> productElements = List.of(exceptBrackets.split(","));
        validateProductElements(productElements);
    }

    private static void validateProductFormatLength(String input) {
        if (input.length() < 2) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "잘못된 입력입니다.");
        }
    }

    private static void validateBrackets(Character front, Character back) {
        if (!front.equals('[') || !back.equals(']')) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "대괄호가 입력에 문제가 있습니다.");
        }
    }

    private static void validateProductElements(List<String> productElements) {
        validateProductElementsSize(productElements);
        int cost = changeToNumber(productElements.get(1));
        validatePrice(cost);
        int count = changeToNumber(productElements.get(2));
    }

    private static void validateProductElementsSize(List<String> productElements) {
        if (productElements.size() != 3) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "상품명, 가격, 수량 3가지만 입력해주세요.");
        }
    }
}
