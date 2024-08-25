package me.study.designpatterns.behavioral.templatemethod.examples.example4_gameai;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        ConsoleUtil.Title("MonstersAI");
        GameAI monstersAI = new MonstersAI();
        monstersAI.turn();
        ConsoleUtil.Title("OrcsAI");
        GameAI orcsAI = new OrcsAI();
        orcsAI.turn();
    }
}