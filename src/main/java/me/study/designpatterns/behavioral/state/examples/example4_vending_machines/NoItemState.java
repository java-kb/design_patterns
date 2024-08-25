package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

class NoItemState implements State {
    private VendingMachine vendingMachine;

    public NoItemState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void requestItem() throws Exception {
        throw new Exception("Item out of stock");
    }

    @Override
    public void addItem(int count) throws Exception {
        vendingMachine.incrementItemCount(count);
        vendingMachine.setState(vendingMachine.getHasItemState());
    }

    @Override
    public void insertMoney(int money) throws Exception {
        throw new Exception("Item out of stock");
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Item out of stock");
    }
}