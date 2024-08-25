package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

class VendingMachine {
    private State hasItem;
    private State itemRequested;
    private State hasMoney;
    private State noItem;

    private State currentState;

    private int itemCount;
    private int itemPrice;

    public VendingMachine(int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;

        hasItem = new HasItemState(this);
        itemRequested = new ItemRequestedState(this);
        hasMoney = new HasMoneyState(this);
        noItem = new NoItemState(this);

        setState(hasItem);
    }

    public void requestItem() throws Exception {
        currentState.requestItem();
    }

    public void addItem(int count) throws Exception {
        currentState.addItem(count);
    }

    public void insertMoney(int money) throws Exception {
        currentState.insertMoney(money);
    }

    public void dispenseItem() throws Exception {
        currentState.dispenseItem();
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void incrementItemCount(int count) {
        System.out.printf("Adding %d items\n", count);
        this.itemCount += count;
    }

    public State getHasItemState() {
        return hasItem;
    }

    public State getItemRequestedState() {
        return itemRequested;
    }

    public State getHasMoneyState() {
        return hasMoney;
    }

    public State getNoItemState() {
        return noItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int count) {
        this.itemCount = count;
    }

    public int getItemPrice() {
        return itemPrice;
    }
}