You are programming a GUI. On the GUI there should be a button that can be activated by 
the user. You pass an ActionListener to the button that contains the code to be exe-
cuted when the button is activated. An object of type ActionListener must override 
the actionPerformed() method. When the button is activated, this method is called. 
To demonstrate the procedure, frst create the button:
 JButton btnExample = new Jbutton(“Non-Sense“);
Then follows an anonymous class for the code to be executed in the action:
ActionListener actionListener = new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 // ... something
 }
 };
And fnally, pass the Command object, the ActionListener, to this button:
 btnExample.addActionListener(actionListener);
The whole thing also works much shorter and clearer with the lambda expressions 
added in Java 8, but does the same thing:
 JButton btnExample = new JButton(“Non-Sense“);
 btnExample.addActionListener((ActionEvent e) -> {
 // … something
 }

 The classes JButton and JMenuItem have the same superclass AbstractButton. 
So, you can reuse the ActionListener from the previous section and add it to the MenuItem 
in the main menu of your interface at the same time:
 JMenuItem mtmExample = new JMenuItem(“Non-Sense“);
 mtmExample.addActionListener(actionListener);
The Action interface enhances the ActionListener interface. You can pass both 
Action and ActionListener to a caller as a command. The binding between the 
caller and the command object is much tighter for an object of type Action. Among 
other things, the Action interface provides the setEnabled() method, which deter-
mines whether the Action object is enabled or disabled. You can also use an action object 
to specify the text and icon of the caller.
