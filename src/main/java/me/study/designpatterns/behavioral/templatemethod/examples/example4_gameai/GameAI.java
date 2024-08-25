package me.study.designpatterns.behavioral.templatemethod.examples.example4_gameai;

import java.util.List;
import java.util.ArrayList;

// The abstract class defines a template method that contains a
// skeleton of some algorithm composed of calls, usually to
// abstract primitive operations. Concrete subclasses implement
// these operations, but leave the template method itself
// intact.
public abstract class GameAI {
    protected List<Structure> builtStructures = new ArrayList<Structure>();

    // The template method defines the skeleton of an algorithm.
    public final void turn() {
        System.out.println("GameAI: turn");
        collectResources();
        buildStructures();
        buildUnits();
        attack();
    }

    // Some of the steps may be implemented right in a base
    // class.
    protected void collectResources() {
        System.out.println("GameAI: collectResources");
        for (Structure s : builtStructures) {
            s.collect();
        }
    }

    // And some of them may be defined as abstract.
    protected abstract void buildStructures();

    protected abstract void buildUnits();

    // A class can have several template methods.
    protected void attack() {
        System.out.println("GameAI: attack");
        Enemy enemy = closestEnemy();
        Map map = new Map();
        if (enemy == null)
            sendScouts(map.center);
        else
            sendWarriors(enemy.position);
    }

    private Enemy closestEnemy() {
        System.out.println("GameAI: closestEnemy");
        return new Enemy();
    }

    protected void sendScouts(long position) {
        System.out.println("GameAI: sendScouts");
    }

    protected void sendWarriors(long position) {
        System.out.println("GameAI: sendWarriors");
    }
}
