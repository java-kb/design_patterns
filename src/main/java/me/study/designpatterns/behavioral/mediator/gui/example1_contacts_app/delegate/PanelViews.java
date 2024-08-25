package me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.delegate;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Mediator"
 */
public class PanelViews extends JSplitPane {

    /**
     * Puts two lists next to each other
     *
     * @param localList the left list with all contacts
     * @param remoteList the right list with the invited contacts
     */
    PanelViews(JList localList, JList remoteList) {
        var pnlAllContacts = new JPanel();
        pnlAllContacts.setLayout(new BorderLayout());
        pnlAllContacts.add(localList, BorderLayout.CENTER);
        pnlAllContacts.setBorder(BorderFactory.
                createTitledBorder("All Contacts"));

        var pnlInvitedContacts = new JPanel();
        pnlInvitedContacts.setLayout(new BorderLayout());
        pnlInvitedContacts.add(remoteList, BorderLayout.CENTER);
        pnlInvitedContacts.setBorder(BorderFactory.
                createTitledBorder("Invited Contacts"));

        this.setLeftComponent(new JScrollPane(pnlAllContacts));
        this.setRightComponent(new JScrollPane(pnlInvitedContacts));
        this.setResizeWeight(0.5);
    }
}
