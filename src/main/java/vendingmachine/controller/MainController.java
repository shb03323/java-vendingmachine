package vendingmachine.controller;

import vendingmachine.repository.VendingMachine;
import vendingmachine.view.InputView;

public class MainController {

    private static final VendingMachine vendingMachine = VendingMachine.getInstance();

    public void process() {

    }

    private void saveMachineHoldingAmount() {
        int amount = InputView.inputMachineHoldingAmount();
        vendingMachine.setAmount(amount);
    }
}
