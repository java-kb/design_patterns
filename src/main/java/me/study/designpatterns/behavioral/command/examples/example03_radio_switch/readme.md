The example project RadioCommand shows how a radio (the older ones among us may 
remember) can be operated with the Command Pattern. Besides the frequency adjustment, 
which I’ll leave out here, there are four very simple commands: turn on, turn off, turn up 
and turn down. All commands implement the interface Command, which specifes two 
methods. The execute() method executes the command, the undo() method returns 
a command that must be executed to undo its own execute method.
 public interface Command {
 void execute();
 Command undo();
 }
I will explain the command for switching on the radio here as a representative of all 
other commands. An object of the type radio is passed to the constructor of the command. 
The command is executed on this object. So if the execute() method is called, the com-
mand turns the radio on. If the undo() method is called, the radio is turned off, so the 
turn on command returns a turn off command.
 public class OnCommand implements Command {
 private fnal Radio radio;
 public OnCommand(Radio radio) {
 this.radio = radio;
 }
 @Override
 public void execute() {
 System.out.println(“The radio will turn on.“);
 radio.turnOn();
 }
 @Override
 public Command undo() {
 return new OffCommand(radio);
 }
 }
The radio must now provide the appropriate methods so that the commands can be 
executed.
 public class Radio {
 private int volume = 0;
 public void turnOn() {
 volume = 1;
 System.out.println(“>Radio: I'm on now.“);
 }
 public void turnOff() {
 volume = 0;
 System.out.println(“>Radio: I'm off now.“);
 }
 public void decreaseVolume() {
 if (volume >= 1) {
 volume--;
System.out.println(“>Radio: I'm playing” +
 “softer: “ + volume);
 }
 }
 public void increaseVolume() {
 volume++;
 System.out.println(“>Radio: I'm playing louder: “
 + volume);
 }
 }
The radio, in Command Pattern terminology, is the receiver that executes the com-
mands. Invoker is a class Logbook to which the context sends the command call and the 
undo. The Invoker logs all command invocations. This allows the context to undo the last 
command in each case.
 public class Logbook {
 private fnal List<Command> history =
 new ArrayList<>();
 public void execute(Command command) {
 history.add(command);
 command.execute();
 }
 public void undo() {
 int size = history.size();
 if (size > 0) {
 Command command = history.remove(size - 1);
 Command undoCommand = command.undo();
 System.out.println(“\tundo: “ + undoCommand);
 undoCommand.execute();
 }
 }
 }
The test class creates an object of the type Radio. In addition, the commands are created 
and parameterized with the radio. The commands are then passed to the logbook and executed.
 Radio radio = new Radio();
 var onCommand = new onCommand(radio);
 var offCommand = new offCommand(radio);
 var softerCommand = new SofterCommand(radio);
 var louderCommand = new LouderCommand(radio);
 var logbook = new Logbook();
 logbook.execute(onCommand);
 // … abridged
 log.undo();