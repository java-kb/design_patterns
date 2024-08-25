package me.study.designpatterns.behavioral.state.examples.example5_3speed_ceiling_fan;

public class CeilingFanPullChainBefore {
    private int currentState;

    public CeilingFanPullChainBefore() {
        currentState = 0;
    }

    public void pull() {
        if (currentState == 0) {
            currentState = 1;
            System.out.println("low speed");
        } else if (currentState == 1) {
            currentState = 2;
            System.out.println("medium speed");
        } else if (currentState == 2) {
            currentState = 3;
            System.out.println("high speed");
        } else {
            currentState = 0;
            System.out.println("turning off");
        }
    }
}
