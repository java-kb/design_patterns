package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JPopupMenu.Separator;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Factory Method"
 */
public class TestApp {

    /**
     * The main window
     */
    private final JFrame frmMain = new JFrame("Address Book - Factory Method");
    /**
     * The area for showing the entries
     */
    private JTabbedPane tbbMain = new JTabbedPane();
    /**
     * The menu
     */
    private final JMenuBar mnMainMenu = new JMenuBar();
    /**
     * Main menu "File"
     */
    private final JMenu mnFile = new JMenu("File");
    /**
     * Menu item for creating a new entry (select in submenu)
     */
    private final JMenu mnNew = new JMenu("New");
    /**
     * Submenu for a new contact
     */
    private final JMenuItem mnContact = new JMenuItem();
    /**
     * Submenu for a new appointment
     */
    private final JMenuItem mnAppointment = new JMenuItem();
    /**
     * List of all entries
     */
    private final List<Entry> entryList = new ArrayList<>();
    /**
     * The menu item to save the current entry
     */
    private final JMenuItem mnSave = new JMenuItem();
    /**
     * The menu item to quit the program
     */
    private final JMenuItem mnQuit = new JMenuItem();
    /**
     * New appointment: create and register
     */
    private final Action newAppointmentAction = new AbstractAction("Appointment") {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerEntry(new Appointment());
        }
    };
    /**
     * New contact: create and register
     */
    private final Action newContactAction = new AbstractAction("Contact") {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerEntry(new Contact());
        }
    };
    /**
     * The action "Save" calls the save method of the entry and updates the
     * title of the panel with the data
     */
    private final Action saveAction = new AbstractAction("Save") {
        @Override
        public void actionPerformed(ActionEvent e) {
            entryList.forEach((tempEntry) -> {
                Object selectedPanel = tbbMain.getSelectedComponent();
                if (tempEntry.getEntryPanel().getEditor() == selectedPanel) {
                    tempEntry.getEntryPanel().save();
                    var index = tbbMain.getSelectedIndex();
                    tbbMain.setTitleAt(index, tempEntry.toString());
                }
            });
        }
    };
    /**
     * On the quit action, just exit
     */
    private final Action quitAction = new AbstractAction("Quit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    /**
     * Constructor: Build the menu and connect it with its actions
     */
    TestApp() {
        mnNew.add(mnContact);
        mnNew.add(mnAppointment);
        mnFile.add(mnNew);
        mnFile.add(mnSave);
        mnFile.add(new Separator());
        mnQuit.setAction(quitAction);
        mnSave.setAction(saveAction);
        mnContact.setAction(newContactAction);
        mnAppointment.setAction(newAppointmentAction);
        mnFile.add(mnQuit);
        frmMain.add(tbbMain);
        mnMainMenu.add(mnFile);
        frmMain.setJMenuBar(mnMainMenu);
        frmMain.setExtendedState(JFrame.NORMAL);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setVisible(true);
    }

    /**
     * Register a new entry an its panel to the application window
     *
     * @param entry the new entry
     */
    private void registerEntry(Entry entry) {
        var viewPanel = entry.getEntryPanel();
        entryList.add(entry);
        tbbMain.add(viewPanel.getEditor(), entry.toString());
    }

    /**
     * Create a factory method and call its constructor
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        new TestApp();
    }
}
