package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class OffCommand implements Command {

    /**
     * The radio where the commands are executed on
     */
    private final Radio radio;

    /**
     * The constructor needs the radio
     *
     * @param radio the radio
     */
    public OffCommand(Radio radio) {
        this.radio = radio;
    }

    /**
     * Make the radio switch off
     */
    @Override
    public void execute() {
        System.out.println("Radio will switch off.");
        radio.turnOff();
    }

    /**
     * Create and return the respective undo command (without executing it)
     *
     * @return the undo command
     */
    @Override
    public Command undo() {
        return new OnCommand(radio);
    }

    /**
     * For printing out the command's name
     *
     * @return name as string
     */
    @Override
    public String toString() {
        return "\"Switch Off\"";
    }
}