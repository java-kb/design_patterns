package me.study.designpatterns.behavioral.state.examples.example5_3speed_ceiling_fan;

import me.study.designpatterns.behavioral.state.examples.example5_3speed_ceiling_fan.TestApp.CeilingFanPullChain;
import me.study.designpatterns.utils.ConsoleUtil;
import java.util.*;

public class TestApp {

    public static void main(String[] args) {
        runBeforeCode();
        runAfterCode();
    }

    private static void runBeforeCode() {
        ConsoleUtil.Title("Without State Pattern");
        CeilingFanPullChainBefore chain = new CeilingFanPullChainBefore();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            chain.pull();
            System.out.println("Press Enter(q to exit):");
            String input = keyboard.nextLine();
            if(input != null) {
                System.out.println("Your input is : " + input);
                if ("q".equals(input)) {
                    System.out.println("Exit programm");
                    exit = true;
                } else if ("x".equals(input)) {
                    //Do something
                }
            }
        }
        
        //keyboard.close();
    }
    private static void runAfterCode() {
        ConsoleUtil.Title("With State Pattern");
        TestApp testApp = new TestApp();
        CeilingFanPullChain  chain = testApp.new CeilingFanPullChain();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            chain.pull();
            System.out.println("Press Enter(q to exit):");
            String input = keyboard.nextLine();
            if(input != null) {
                System.out.println("Your input is : " + input);
                if ("q".equals(input)) {
                    System.out.println("Exit programm");
                    exit = true;
                } else if ("x".equals(input)) {
                    //Do something
                }
            }
        }
        keyboard.close();
    }
    interface State {
        void pull(CeilingFanPullChain wrapper);
    }
    
    class CeilingFanPullChain {
        private State currentState;
    
        public CeilingFanPullChain() {
            currentState = new Off();
        }
    
        public void setState(State s) {
            currentState = s;
        }
    
        public void pull() {
            currentState.pull(this);
        }
    }
    
    class Off implements State {
        public void pull(CeilingFanPullChain wrapper) {
            wrapper.setState(new Low());
            System.out.println("low speed");
        }
    }
    
    class Low implements State {
        public void pull(CeilingFanPullChain wrapper) {
            wrapper.setState(new Medium());
            System.out.println("medium speed");
        }
    }
    
    class Medium implements State {
        public void pull(CeilingFanPullChain wrapper) {
            wrapper.setState(new High());
            System.out.println("high speed");
        }
    }
    
    class High implements State {
        public void pull(CeilingFanPullChain wrapper) {
            wrapper.setState(new Off());
            System.out.println("turning off");
        }
    }
}
