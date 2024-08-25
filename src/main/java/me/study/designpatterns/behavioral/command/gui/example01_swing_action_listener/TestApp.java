package me.study.designpatterns.behavioral.command.gui.example01_swing_action_listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TestApp {
    /**
     * Creates a new GUI
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        new GUI();
    }
}

class GUI extends JFrame {

    /**
     * The constructor creates the user interface
     */
    public GUI() {
        this.setTitle("Command Pattern Demo");

        // Main menu
        var menu = new JMenuBar();
        this.setJMenuBar(menu);

        // File menu
        var mnFile = new JMenu("File");
        menu.add(mnFile);

        // Two items in the File menu
        var mnDisable = new JMenuItem();
        var mnClose = new JMenuItem();
        mnFile.add(mnDisable);
        mnFile.add(mnClose);

        // Two buttons on the panel
        var btnDisable = new JButton();
        var btnClose = new JButton();

        // Use BorderLayout
        var pnlMain = new JPanel();
        var pnlFooter = new JPanel();
        this.getContentPane().add(pnlMain);
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add("South", pnlFooter);
        pnlFooter.add(btnDisable);
        pnlFooter.add(btnClose);

        // Two commands
        var actDisable = new AbstractAction("Disable") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                this.setEnabled(false);
            }
        };

        var actClose = new AbstractAction("Close program") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        };

        // Assign the commands to the invokers
        btnDisable.setAction(actDisable);
        mnDisable.setAction(actDisable);

        btnClose.setAction(actClose);
        mnClose.setAction(actClose);

        this.setLocationRelativeTo(null);
        this.setSize(300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}