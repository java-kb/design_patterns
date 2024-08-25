package me.study.designpatterns.behavioral.templatemethod.examples.example4_gameai;

public class OrcsAI extends GameAI{

    @Override
    protected void buildStructures() {
        //if (there are some resources) then
            // Build farms, then barracks, then stronghold.
            System.out.println("OrcsAI: buildStructures");
    }

    @Override
    protected void buildUnits() {
        // if (there are plenty of resources) then
        // if (there are no scouts)
        //     // Build peon, add it to scouts group.
        // else
        //     // Build grunt, add it to warriors group.
        System.out.println("OrcsAI: buildUnits");
    }

    @Override
    protected void sendScouts(long position) {
        //if (scouts.length > 0) then
        // Send scouts to position.
        System.out.println("OrcsAI: sendScouts");
    }

    @Override
    protected void sendWarriors(long position) {
        //if (warriors.length > 5) then
        // Send warriors to position.
        System.out.println("OrcsAI: sendWarriors");
    }

}
