package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

class HasMoneyState implements State {
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void requestItem() throws Exception {
        throw new Exception("Item dispense in progress");
    }

    @Override
    public void addItem(int count) throws Exception {
        throw new Exception("Item dispense in progress");
    }

    @Override
    public void insertMoney(int money) throws Exception {
        throw new Exception("Item out of stock");
    }

    @Override
    public void dispenseItem() throws Exception {
        System.out.println("Dispensing Item");
        vendingMachine.setItemCount(vendingMachine.getItemCount() - 1);
        if (vendingMachine.getItemCount() == 0) {
            vendingMachine.setState(vendingMachine.getNoItemState());
        } else {
            vendingMachine.setState(vendingMachine.getHasItemState());
        }
    }
}