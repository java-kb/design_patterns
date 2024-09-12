package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;

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
public class Contact implements Entry {

    /**
     * First name of the contact
     */
    private String firstname = "<first name>";
    /**
     * Last name of the contact
     */
    private String lastname = "<last name>";
    /**
     * Birthdate of the contact
     */
    private LocalDate birthday = LocalDate.now();
    /**
     * The editor panel for the contact data
     */
    private final EntryPanel entryPanel = new MyEditor();

    /**
     * Deliver the panel
     *
     * @return the editor panel
     */
    @Override
    public EntryPanel getEntryPanel() {
        return entryPanel;
    }

    /**
     * The description is a combination of the name parts
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return firstname + " " + lastname;
    }

    /**
     * Inner class for the definition of the panel to edit the data and put them
     * back to the object (on save)
     */
    private class MyEditor implements EntryPanel {

        /**
         * Start with a JPanel
         */
        private final JPanel pnlEntry = new JPanel();
        /**
         * First name is put into a text field
         */
        private final JTextField edtfirstname = new JTextField("<Please enter the first name>");
        /**
         * Last name is put into a text field
         */
        private final JTextField edtlastname = new JTextField("<Please enter the last name>");
        /**
         * The date will be selectable via a spinner
         */
        private final JSpinner spnBirthday = new JSpinner();

        /**
         * Constructor. Put all things together in a two column layout
         */
        MyEditor() {
            var focusListener = new MyFocusListener();
            edtfirstname.addFocusListener(focusListener);
            edtlastname.addFocusListener(focusListener);
            pnlEntry.setLayout(new TwoColumnLayout(20, 10));
            pnlEntry.add(new JLabel("First name: "));
            pnlEntry.add(edtfirstname);
            pnlEntry.add(new JLabel("Last name: "));
            pnlEntry.add(edtlastname);
            pnlEntry.add(new JLabel("Birthday: "));
            pnlEntry.add(spnBirthday);
            var lmodel = new SpinnerDateModel(Date.from(birthday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), null, null, 0);
            spnBirthday.setModel(lmodel);
            spnBirthday.setEditor(new JSpinner.DateEditor(spnBirthday, "MM/dd/yyyy"));
        }

        /**
         * Push data from the panel back to the object (save)
         */
        @Override
        public void save() {
            firstname = edtfirstname.getText();
            lastname = edtlastname.getText();
            var dob = (Date) spnBirthday.getValue();
            birthday = Instant.ofEpochMilli(dob.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        /**
         * Deliver the panel as editor
         *
         * @return the panel
         */
        @Override
        public JPanel getEditor() {
            return pnlEntry;
        }
    }

    /**
     * The description of the panel
     *
     * @return text
     */
    @Override
    public String toString() {
        return getDescription();
    }
}
