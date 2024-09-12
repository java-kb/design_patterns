package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
public class House {

    /**
     * Where the game starts
     */
    private Room entry;
    /**
     * The possible player commands
     */
    private final List<Command> commands = new ArrayList<>();

    /**
     * Build the house, create rooms, set doors
     */
    private void createHouse() {
        var hallway = new Room("hallway", "in the hallway");
        // It all starts in the hallway
        entry = hallway;
        var corridor = new Room("corridor", "in the corridor");
        var bathroom = new Room("bathroom", "in the bathroom");
        var study = new Room("study", "in the study");
        var livingroom = new Room("living room", "in the living room");
        var kitchen = new Room("kitchen", "in the kitchen");
        var pantry = new Room("pantry", "in the pantry");
        var library = new Room("library", "in the library");

        // First, specify the doors between the rooms
        var door = new Door[7];
        door[0] = new Door(hallway, corridor);
        door[1] = new Door(hallway, bathroom);
        door[2] = new Door(corridor, library);
        door[3] = new Door(corridor, kitchen);
        door[4] = new Door(kitchen, pantry);
        door[5] = new Door(corridor, livingroom);
        door[6] = new Door(livingroom, study);

        // Then place the doors
        hallway.addComponent(Direction.NORTH, door[0]);
        hallway.addComponent(Direction.WEST, door[1]);
        corridor.addComponent(Direction.NORTH, door[2]);
        corridor.addComponent(Direction.EAST, door[3]);
        kitchen.addComponent(Direction.NORTH, door[4]);
        corridor.addComponent(Direction.WEST, door[5]);
        livingroom.addComponent(Direction.NORTH, door[6]);
    }

    /**
     * Comstructor: Specify five possible commands, then create the house
     */
    private House() {
        commands.add(new OpenDoor());
        commands.add(new EnterDoor());
        commands.add(new Look());
        commands.add(new Exit());
        commands.add(new Nothing());
        createHouse();
    }

    /**
     * Enter the house, look around and take commands until the game is quit
     */
    private void enter() {
        System.out.println(entry.describe());
        var player = new Player();
        entry.enter(player);

        var scanner = new Scanner(System.in);
        String command;
        System.out.print("> ");
        // Wait for the enter key to be pressed
        while (!((command = scanner.nextLine()) == null)) {
            // Push input into command line
            var cmdLine = new CmdLine(command);
            // Check, if any command has been entered
            for (var cmd : commands)
                if (cmd.matches(cmdLine)) {
                    cmd.handle(cmdLine, player);
                    break;
                }
            System.out.print("> ");
        }
    }

    /**
     * A command line is split into single words. Every command knows how it has
     * to look like and can check itself against the user's wish
     */
    public static class CmdLine {

        /**
         * The individual parts of a command
         */
        private final String[] tokens;

        /**
         * Split an input into individual words
         *
         * @param nextLine the player's input
         */
        public CmdLine(String nextLine) {
            this.tokens = nextLine.split(" ");
        }

        /**
         * Check, if the input starts with a specific sequence of words
         *
         * @param tokens the word sequence to check against the input
         *
         * @return true, if the input starts with the same given words
         */
        public boolean startsWith(String... tokens) {
            if (this.tokens.length < tokens.length)
                return false;

            for (var i = 0; i < tokens.length; i++)
                if (!this.tokens[i].equalsIgnoreCase(tokens[i]))
                    return false;

            return true;
        }

        /**
         * Check, if there's a direction given at a specific position in a
         * command
         *
         * @param index position to be checked
         *
         * @return true, if there's a direction at this position
         */
        public boolean hasDirection(int index) {
            Direction d;
            try {
                d = Direction.getDirection(tokens[index]);
            } catch (Exception e) {
                return false;
            }
            if (d == Direction.UNKNOWN)
                return false;
            return true;
        }

        /**
         * Convert a word into a direction
         *
         * @param index position of the word
         *
         * @return the direction
         */
        public Direction getAsDirection(int index) {
            return Direction.getDirection(tokens[index]);
        }
    }

    /**
     * A command can check itself against the input and it can be executed
     */
    public interface Command {

        /**
         * Check the commands against a specific input
         *
         * @param cmdLine the input
         *
         * @return true, if input matches a command
         */
        public boolean matches(CmdLine cmdLine);

        /**
         * Execute the command
         *
         * @param cmdLine the command
         * @param player the player
         */
        public void handle(CmdLine cmdLine, Player player);
    }

    /**
     * Open a door
     */
    public class OpenDoor implements Command {

        /**
         * The command starts with "open door"
         *
         * @param cmdLine the input
         *
         * @return result of the check
         */
        @Override
        public boolean matches(CmdLine cmdLine) {
            return cmdLine.startsWith("open", "door");
        }

        /**
         * If there's a door in the given direction, it can be opened
         *
         * @param cmdLine the command
         * @param player the player
         */
        @Override
        public void handle(CmdLine cmdLine, Player player) {
            if (cmdLine.hasDirection(2)) {
                var direction = cmdLine.getAsDirection(2);
                var currentRoom = player.getCurrentRoom();
                var element = currentRoom.getElementAt(direction);
                // Pattern-Matching with instanceof
                // automatically casts the element into the variable door
                // Available as a preview feature since Java 14, standard since Java 16
                if (element instanceof Door door)
                    door.open();
                else
                    System.out.println("I wonder how you would open this!");

                // "old Version" from before "Java 14 preview":
//                if (element instanceof Door) {
//                    var door = (Door) element;
//                    door.open();
//                } else
//                    System.out.println("I wonder how you would open this!");
            } else
                System.out.println("Which door would you like to open?");
        }
    }

    /**
     * Walkt through a door
     */
    public class EnterDoor implements Command {

        /**
         * This command start with "enter door"
         *
         * @param cmdLine the input
         *
         * @return result of the check
         */
        @Override
        public boolean matches(CmdLine cmdLine) {
            return cmdLine.startsWith("enter", "door");
        }

        /**
         * Walk through the door in the given direction
         *
         * @param cmdLine the input
         * @param player the player
         */
        @Override
        public void handle(CmdLine cmdLine, Player player) {
            if (cmdLine.hasDirection(2)) {
                var direction = cmdLine.getAsDirection(2);
                var currentRoom = player.getCurrentRoom();
                var element = currentRoom.getElementAt(direction);
                if (element instanceof Door door)
                    door.enter(player);
                else
                    System.out.println("Ouch?");
            } else
                System.out.println("Which door would you like to walk through?");
        }
    }

    /**
     * Look around
     */
    public class Look implements Command {

        /**
         * This command is a simple "look"
         *
         * @param cmdLine the input
         *
         * @return result of the check
         */
        @Override
        public boolean matches(CmdLine cmdLine) {
            return cmdLine.startsWith("look");
        }

        /**
         * Describe the current room
         *
         * @param cmdLine the input
         * @param player the player
         */
        @Override
        public void handle(CmdLine cmdLine, Player player) {
            System.out.println(player.getCurrentRoom().describe());
        }
    }

    /**
     * Quit the game
     */
    public class Exit implements Command {

        /**
         * this command is a simple "exit"
         *
         * @param cmdLine the command
         *
         * @return result of the check
         */
        @Override
        public boolean matches(CmdLine cmdLine) {
            return cmdLine.startsWith("exit");
        }

        /**
         * Quit the application
         *
         * @param cmdLine the input
         * @param player the player
         */
        @Override
        public void handle(CmdLine cmdLine, Player player) {
            System.exit(0);
        }
    }

    /**
     * Handle any other input
     */
    public class Nothing implements Command {

        /**
         * Whatever has been entered
         *
         * @param cmdLine the input
         *
         * @return always true
         */
        @Override
        public boolean matches(CmdLine cmdLine) {
            return true;
        }

        /**
         * Unknown command. Get back to the player
         *
         * @param cmdLine the input
         * @param player the player
         */
        @Override
        public void handle(CmdLine cmdLine, Player player) {
            System.out.println("What would you like to do?");
        }
    }

    /**
     * Build a house and enter it
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var house = new House();
        house.enter();
    }
}
