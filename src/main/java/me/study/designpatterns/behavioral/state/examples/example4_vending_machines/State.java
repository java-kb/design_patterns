package me.study.designpatterns.behavioral.state.examples.example4_vending_machines;

interface State {
    void addItem(int count) throws Exception;

    void requestItem() throws Exception;

    void insertMoney(int money) throws Exception;

    void dispenseItem() throws Exception;
}