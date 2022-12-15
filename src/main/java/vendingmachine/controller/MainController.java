package vendingmachine.controller;

import vendingmachine.service.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {

    private final VendingMachine vendingMachine;

    public MainController() {
        this.vendingMachine = new VendingMachine();
    }

    public void process() {
        saveMachineHoldingAmount();
        addProducts();
        insertMoney();
        purchase();
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

    private void purchase() {
        try {
            while (vendingMachine.availableToPurchase()) {
                purchaseProduct();
            }
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            purchase();
        }
    }

    private void purchaseProduct() {
        String productName = InputView.inputPurchasingProduct();
        vendingMachine.purchaseProduct(productName);
        OutputView.printMoneyLeft(vendingMachine.getInsertedMoney());
    }

    private void getResult() {
        OutputView.printResult(vendingMachine.getChanges());
    }
}
