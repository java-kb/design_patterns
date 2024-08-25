package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

import java.util.logging.Logger;
import java.util.logging.Level;

public class TestApp {
    private static final Logger LOGGER = Logger.getLogger(TestApp.class.getName());

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(1, 10);

        try {
            vendingMachine.requestItem();
            vendingMachine.insertMoney(10);
            vendingMachine.dispenseItem();

            System.out.println();

            vendingMachine.addItem(2);

            System.out.println();

            vendingMachine.requestItem();
            vendingMachine.insertMoney(10);
            vendingMachine.dispenseItem();

            System.out.println();

            vendingMachine = new VendingMachine(0, 10);
            vendingMachine.requestItem();
            vendingMachine.insertMoney(10);
            vendingMachine.dispenseItem();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
