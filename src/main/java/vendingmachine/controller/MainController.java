package vendingmachine.controller;

import vendingmachine.service.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {

    private static final VendingMachine vendingMachine = VendingMachine.getInstance();

    public void process() {
        saveMachineHoldingAmount();
        addProducts();
        insertMoney();
        purchaseProduct();
        getResult();
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
        try {
            String productsInput = InputView.inputProducts();
            vendingMachine.setProducts(productsInput);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            addProducts();
        }
    }

    private void insertMoney() {
        try {
            int money = InputView.inputMoney();
            vendingMachine.insertMoney(money);
            OutputView.printMoneyLeft(money);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            insertMoney();
        }
    }

    private void purchaseProduct() {
        try {
            while (vendingMachine.availableToPurchase()) {
                String productName = InputView.inputPurchasingProduct();
                vendingMachine.purchaseProduct(productName);
                OutputView.printMoneyLeft(vendingMachine.getInsertedMoney());
            }
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            purchaseProduct();
        }
    }

    private void getResult() {
        OutputView.printResult(vendingMachine.getChanges());
    }
}
