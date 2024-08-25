package me.study.designpatterns.utils;

public class ConsoleUtil {
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_RED = "\u001B[41m";

    // Main driver method
    public static void Title(String message) {
        // Printing the text on console prior adding
        // the desired color
        System.out.println(ANSI_RED
                + message
                + ANSI_RESET);
    }
}
