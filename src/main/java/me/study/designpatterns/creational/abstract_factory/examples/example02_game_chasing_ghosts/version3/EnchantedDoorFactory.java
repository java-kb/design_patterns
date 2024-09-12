package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version3;

import java.util.ArrayList;
import java.util.List;

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
public class EnchantedDoorFactory extends ComponentFactory {

    /**
     * Questions are a inner class which also contains the answers
     */
    public static class Question {

        /**
         * The question text
         */
        public final String question;
        /**
         * The answer text
         */
        public final String answer;

        /**
         * Constructor. Assign fields
         *
         * @param question
         * @param answer
         */
        private Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }
    /**
     * A list of possible questions
     */
    private final List<Question> questions = new ArrayList<>();

    // Initialize
    {
        questions.add(new Question("Who's the elder brother of Winne-tou?", "Winne-one"));
        questions.add(new Question("Speak FRIEND and enter!", "mellon"));
        questions.add(new Question("GIMME DA PASSWORD!", "KenSentMe"));
        questions.add(new Question("What's the answer to everything?", "42"));
    }

    /**
     * New constructor. Select and assign a question randomly
     *
     * @param room1 from
     * @param room2 to
     *
     * @return the door, either a normal one or an enchanted one
     */
    @Override
    Door createDoor(Room room1, Room room2) {
        var number = (int) (Math.random() * 10);
        if (number > (questions.size() - 1))
            return new Door(room1, room2);
        else
            return new EnchantedDoor(room1, room2, questions.get(number));
    }
}
