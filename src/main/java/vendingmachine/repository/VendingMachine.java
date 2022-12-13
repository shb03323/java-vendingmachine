package vendingmachine.repository;

public class VendingMachine {

    private static int amount;

    private static VendingMachine instance;

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    private VendingMachine() {}

    public void setAmount(int inputAmount) {
        amount = inputAmount;
    }
}
