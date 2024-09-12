package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version4;


import java.util.Scanner;

import me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version4.EnchantedDoorFactory.Question;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Abstract Factory"
 */
public class EnchantedDoor extends Door {

    /**
     * A question that needs to be answered to open the door
     */
    private final Question question;

    /**
     * Constructor. Build the door from the superclass, but remember the
     * question
     *
     * @param room1 from
     * @param room2 to
     * @param question only open on the correct answer to this question
     */
    EnchantedDoor(Room room1, Room room2, Question question) {
        super(room1, room2);
        this.question = question;
    }

    /**
     * First, ask the question. Only open on correct answer
     */
    @Override
    void open() {
        if (super.isOpen())
            System.out.println("The door is already open");
        else {
            var scanner = new Scanner(System.in);
            System.out.print(question.question + " >> ");
            var input = scanner.nextLine();
            if (input.equalsIgnoreCase(question.answer))
                super.open();
            else
                System.out.println("This door stays closed.");
        }
    }
}
