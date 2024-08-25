package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class LouderCommand implements Command {

    /**
     * The radio where the commands are executed on
     */
    private final Radio radio;

    /**
     * The constructor needs the radio
     *
     * @param radio the radio
     */
    public LouderCommand(Radio radio) {
        this.radio = radio;
    }

    /**
     * Let the radio get louder
     */
    @Override
    public void execute() {
        System.out.println("Radio will get louder.");
        radio.increaseVolume();
    }

    /**
     * Create and return the respective undo command (without executing it)
     *
     * @return the undo command
     */
    @Override
    public Command undo() {
        return new SofterCommand(radio);
    }

    /**
     * For printing out the command's name
     *
     * @return name as string
     */
    @Override
    public String toString() {
        return "\"Louder\"";
    }
}