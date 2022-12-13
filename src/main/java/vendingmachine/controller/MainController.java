package vendingmachine.controller;

import vendingmachine.service.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {

    private static final VendingMachine vendingMachine = VendingMachine.getInstance();

    public void process() {
        saveMachineHoldingAmount();
    }

    private void saveMachineHoldingAmount() {
        try {
            int amount = InputView.inputMachineHoldingAmount();
            vendingMachine.setAmount(amount);
            OutputView.printCoinsOfVendingMachine(vendingMachine.getCoins());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            saveMachineHoldingAmount();
        }
    }

    private void addProducts() {

    }
}
