package me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.delegate;

import javax.swing.JButton;
import javax.swing.JList;

import me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.model.AllContactsModel;
import me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.model.InvitedContactsModel;

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
class Mediator {

    private JButton btnInvite;
    private JButton btnDisinvite;
    private JButton btnDelete;
    private JList allContactsList;
    private JList invitedContactsList;

    /**
     * Register the invite button at the mediator
     *
     * @param btnInvite reference to the invite button
     */
    void registerInviteButton(JButton btnInvite) {
        this.btnInvite = btnInvite;
    }

    /**
     * Register the disinvite button at the mediator
     *
     * @param btnDisinvite reference to the disinvite button
     */
    void registerDisinviteButton(JButton btnDisinvite) {
        this.btnDisinvite = btnDisinvite;
    }

    /**
     * Registers the delete button at the mediator
     *
     * @param btnDelete reference to the delete button
     */
    void registerDeleteButton(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    /**
     * Registers the "all contacts list" at the mediator
     *
     * @param allContactsList reference to the list with all contacts
     */
    void registerAllContactsList(JList allContactsList) {
        this.allContactsList = allContactsList;
    }

    /**
     * Registers the "invited contacts list" at the mediator
     *
     * @param invitedContactsList reference to the list with the invited ones
     */
    void registerInvitedContactsList(JList invitedContactsList) {
        this.invitedContactsList = invitedContactsList;
    }

    /**
     * Moves a contact to the invited list
     */
    void invite() {
        btnInvite.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisinvite.setEnabled(false);

        var selectedItem = (String) allContactsList.getSelectedValue();

        var tempModel = allContactsList.getModel();
        var allContactsModel = (AllContactsModel) tempModel;

        tempModel = invitedContactsList.getModel();
        var invitedContactsModel = (InvitedContactsModel) tempModel;

        allContactsModel.removeData(selectedItem);
        invitedContactsModel.addData(selectedItem);

        allContactsList.clearSelection();
        invitedContactsList.clearSelection();
    }

    /**
     * Removes a contact from the invited list back to the all contacts list
     */
    void disinvite() {
        btnInvite.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisinvite.setEnabled(false);

        var selectedItem = (String) invitedContactsList
                .getSelectedValue();

        var allContactsModel = (AllContactsModel) allContactsList
                .getModel();

        var invitedContactsModel = (InvitedContactsModel) invitedContactsList
                .getModel();

        invitedContactsModel.removeData(selectedItem);
        allContactsModel.addData(selectedItem);

        allContactsList.clearSelection();
        invitedContactsList.clearSelection();
    }

    /**
     * Deletes a selected contact
     */
    void deleteContact() {
        btnInvite.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisinvite.setEnabled(false);

        var selectedItem = (String) allContactsList.getSelectedValue();
        if (selectedItem != null) {
            var allContactsModel = (AllContactsModel) allContactsList
                    .getModel();
            allContactsModel.removeData(selectedItem);
        }

        selectedItem = (String) invitedContactsList.getSelectedValue();
        if (selectedItem != null) {
            var invitedContactsModel = (InvitedContactsModel) invitedContactsList
                    .getModel();
            invitedContactsModel.removeData(selectedItem);
        }

        allContactsList.clearSelection();
        invitedContactsList.clearSelection();
    }

    /**
     * Gets executed, when a selection in the all contacts list changes
     */
    void selectAllContacts() {
        var index = allContactsList.getSelectedIndex();
        if (index >= 0) {
            btnInvite.setEnabled(true);
            btnDelete.setEnabled(true);
            btnDisinvite.setEnabled(false);
            invitedContactsList.clearSelection();
        }
    }

    /**
     * Gets executed, when a selection in the invited list changes
     */
    void selectInvitedContacts() {
        var index = invitedContactsList.getSelectedIndex();
        if (index >= 0) {
            btnInvite.setEnabled(false);
            btnDelete.setEnabled(true);
            btnDisinvite.setEnabled(true);
            allContactsList.clearSelection();
        }
    }

    /**
     * Close the program
     */
    void exit() {
        System.exit(0);
    }
}
