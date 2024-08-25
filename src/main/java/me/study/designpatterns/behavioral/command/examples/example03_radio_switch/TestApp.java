package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class TestApp {
    /**
     * A testing sequence for the radio, a log book and some commands
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        // Create the radio
        var radio = new Radio();

        // Create and assign the commands
        var onCommand = new OnCommand(radio);
        var offCommand = new OffCommand(radio);
        var softerCommand = new SofterCommand(radio);
        var louderCommand = new LouderCommand(radio);

        // Create a log book for the commands
        var logbook = new Logbook();

        // Using the logbook, execute some commands and undos
        logbook.execute(onCommand);
        logbook.execute(louderCommand);
        logbook.execute(louderCommand);
        logbook.execute(softerCommand);
        logbook.undo();
        logbook.undo();
        logbook.execute(onCommand);
        logbook.undo();
    }
}
