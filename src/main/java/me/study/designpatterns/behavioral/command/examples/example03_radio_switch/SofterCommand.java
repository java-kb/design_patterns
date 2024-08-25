package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class SofterCommand implements Command {

    /**
     * The radio where the commands are executed on
     */
    private final Radio radio;

    /**
     * The constructor needs the radio
     *
     * @param radio the radio
     */
    public SofterCommand(Radio radio) {
        this.radio = radio;
    }

    /**
     * Let the radio get quieter
     */
    @Override
    public void execute() {
        System.out.println("The radio will get quieter.");
        radio.decreaseVolume();
    }

    /**
     * Create and return the respective undo command (without executing it)
     *
     * @return the undo command
     */
    @Override
    public Command undo() {
        return new LouderCommand(radio);
    }

    /**
     * For printing out the command's name
     *
     * @return name as string
     */
    @Override
    public String toString() {
        return "\"Softer\"";
    }
}