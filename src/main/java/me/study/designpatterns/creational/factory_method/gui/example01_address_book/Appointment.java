package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
public class Appointment implements Entry {

    /**
     * Date of the appointment
     */
    private LocalDate lday = LocalDate.now();
    /**
     * The person you're meeting with
     */
    private String friend = "<Please enter the name of your friend>";
    /**
     * The editor panel for the appointment
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
     * A description is combined from date and contact name
     *
     * @return text
     */
    @Override
    public String getDescription() {
        var ldateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        var lstrDate = lday.format(ldateFormat) + " with " + friend;
        return lstrDate;
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
         * Contact person is put into a text field
         */
        private final JTextField edtFriend = new JTextField(friend);
        /**
         * The date will be selectable via a spinner
         */
        private final JSpinner spnAppointment = new JSpinner();

        /**
         * Constructor. Put all things together in a two column layout
         */
        MyEditor() {
            var focusListener = new MyFocusListener();
            edtFriend.addFocusListener(focusListener);
            pnlEntry.setLayout(new TwoColumnLayout(20, 10));
            pnlEntry.add(new JLabel("Dated with: "));
            pnlEntry.add(edtFriend);
            pnlEntry.add(new JLabel("Date: "));
            pnlEntry.add(spnAppointment);
            var lmodel = new SpinnerDateModel(Date.from(lday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), null, null, 0);
            spnAppointment.setModel(lmodel);
            spnAppointment.setEditor(new JSpinner.DateEditor(spnAppointment, "MM/dd/yyyy"));
        }

        /**
         * Push data from the panel back to the object (save)
         */
        @Override
        public void save() {
            friend = edtFriend.getText();
            var day = (Date) spnAppointment.getValue();
            lday = Instant.ofEpochMilli(day.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            System.out.println("saved");

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
