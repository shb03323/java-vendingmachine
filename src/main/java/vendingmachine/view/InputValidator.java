package vendingmachine.view;

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

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
