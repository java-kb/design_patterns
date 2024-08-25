package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

class ItemRequestedState implements State {
    private VendingMachine vendingMachine;

    public ItemRequestedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void requestItem() throws Exception {
        throw new Exception("Item already requested");
    }

    @Override
    public void addItem(int count) throws Exception {
        throw new Exception("Item Dispense in progress");
    }

    @Override
    public void insertMoney(int money) throws Exception {
        if (money < vendingMachine.getItemPrice()) {
            throw new Exception("Inserted money is less. Please insert " + vendingMachine.getItemPrice());
        }
        System.out.println("Money entered is ok");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please insert money first");
    }
}