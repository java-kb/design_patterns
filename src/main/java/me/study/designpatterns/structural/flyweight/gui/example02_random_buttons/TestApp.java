package me.study.designpatterns.structural.flyweight.gui.example02_random_buttons;

import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
// https://sourcemaking.com/design_patterns/flyweight/java/2
public class TestApp {
    public static void main(String[] args) {
        Random rn = new Random();
        Frame frame = new Frame("Flyweight Demo");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(new GridLayout(10, 10));
        // 1. Identify shareable and non-shareable state
        // shareable - Button label, non-shareable - Button location
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                // 3. The client must use the Factory to request objects
                frame.add(FlyweightFactory.makeButton(
                        Integer.toString(rn.nextInt(15))));
        frame.pack();
        frame.setVisible(true);
        FlyweightFactory.report();
    }
}

class FlyweightFactory {
    private static Map treeMap = new TreeMap();
    private static int sharedButtons = 0;
    private static ButtonListener listener = new ButtonListener();

    public static Button makeButton(String num) {
        Button button;
        if (treeMap.containsKey(num)) {
            // 1. Identify intrinsic state (Button label)
            // 2. Return an existing object [The same Button cannot be added
            // multiple times to a container, and, Buttons cannot be cloned.
            // So - this is only simulating the sharing that the Flyweight
            // pattern provides.]
            button = new Button(((Button) treeMap.get(num)).getLabel());
            sharedButtons++;
        } else {
            // 2. Return a new object
            button = new Button(num);
            treeMap.put(num, button);
        }
        button.addActionListener(listener);
        return button;
    }

    public static void report() {
        System.out.print("new Buttons - " + treeMap.size()
                + ", \"shared\" Buttons - " + sharedButtons + ", ");
        for (Object o : treeMap.keySet()) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}

class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        Component[] buttons = button.getParent().getComponents();
        int i = 0;
        for (; i < buttons.length; i++) {
            if (button == buttons[i]) {
                break;
            }
        }
        // 4. A third party must compute the extrinsic state (x and y)
        // (the Button label is intrinsic state)
        System.out.println("label-" + e.getActionCommand()
                + "  x-" + i / 10 + "  y-" + i % 10);
    }
}