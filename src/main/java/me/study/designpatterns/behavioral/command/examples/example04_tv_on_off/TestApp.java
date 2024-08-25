package me.study.designpatterns.behavioral.command.examples.example04_tv_on_off;

//Client code
public class TestApp {
    public static void main(String[] args) {
        TV samsung = new TV();
        OnCommand onCommand = new OnCommand(samsung);
        OffCommand offCommand = new OffCommand(samsung);
        onCommand.execute();
        offCommand.execute();
    }
}

/**
 * Command
 */
interface Command {

    public void execute();
}
//Concrete command
class OnCommand implements Command {
    Device device;

    public OnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.on();
    }

}
//Concrete command
class OffCommand implements Command {
    Device device;

    public OffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.off();
    }

}
//Receiver interface
interface Device {
    public void on();

    public void off();
}
//Concrete receiver
class TV implements Device {
    private boolean isRunning = false;

    @Override
    public void on() {
        isRunning = true;
        System.out.println("Turning Tv On");
    }

    @Override
    public void off() {
        isRunning = false;
        System.out.println("Turning Tv Off");
    }

}
