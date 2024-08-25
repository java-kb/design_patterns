package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class OnCommand implements Command {

    /**
     * The radio where the commands are executed on
     */
    private final Radio radio;

    /**
     * The constructor needs the radio
     *
     * @param radio the radio
     */
    public OnCommand(Radio radio) {
        this.radio = radio;
    }

    /**
     * Make the radio switch on
     */
    @Override
    public void execute() {
        System.out.println("The radio will turn on.");
        radio.turnOn();
    }

    /**
     * Create and return the respective undo command (without executing it)
     *
     * @return the undo command
     */
    @Override
    public Command undo() {
        return new OffCommand(radio);
    }

    /**
     * For printing out the command's name
     *
     * @return name as string
     */
    @Override
    public String toString() {
        return "\"On\"";
    }
}