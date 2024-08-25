package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

class HasItemState implements State {
    private VendingMachine vendingMachine;

    public HasItemState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void requestItem() throws Exception {
        if (vendingMachine.getItemCount() == 0) {
            vendingMachine.setState(vendingMachine.getNoItemState());
            throw new Exception("No item present");
        }
        System.out.println("Item requested");
        vendingMachine.setState(vendingMachine.getItemRequestedState());
    }

    @Override
    public void addItem(int count) throws Exception {
        System.out.printf("%d items added\n", count);
        vendingMachine.incrementItemCount(count);
    }

    @Override
    public void insertMoney(int money) throws Exception {
        throw new Exception("Please select item first");
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please select item first");
    }
}