package me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.delegate;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

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
public class GUI {

    private final JFrame frmMain = new JFrame();

    /**
     * Construct the user interface
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public GUI() throws Exception {
        frmMain.setTitle("Demo Mediator Pattern");
        final var mediator = new Mediator();
        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Add the buttons to the GUI
        var btnInvite = new JButton("Invite");
        btnInvite.setEnabled(false);
        mediator.registerInviteButton(btnInvite);

        /**
         * Writing an ActionListener and event assignments got quite elegant
         * since the invention of Lambda expressions
         */
        btnInvite.addActionListener((ActionEvent e) -> {
            mediator.invite();
        });

        // This is the "classic" syntax of an ActionListener
//        btnInvite.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                mediator.invite();
//            }
//        });
        var btnDisinvite = new JButton("Disinvite");
        btnDisinvite.setEnabled(false);
        mediator.registerDisinviteButton(btnDisinvite);
        btnDisinvite.addActionListener((ActionEvent e) -> {
            mediator.disinvite();
        });

        var btnDelete = new JButton("Delete");
        btnDelete.setEnabled(false);
        mediator.registerDeleteButton(btnDelete);
        btnDelete.addActionListener((ActionEvent e) -> {
            mediator.deleteContact();
        });

        var btnExit = new JButton("Exit");
        btnExit.addActionListener((ActionEvent e) -> {
            mediator.exit();
        });

        var lstPanel = new JPanel();
        lstPanel.add(btnInvite);
        lstPanel.add(btnDisinvite);
        lstPanel.add(btnDelete);
        lstPanel.add(btnExit);
        frmMain.add(lstPanel, BorderLayout.SOUTH);

        // Create the lists and add them to the GUI
        var allContactsList = new JList();
        allContactsList.setModel(new AllContactsModel());
        allContactsList.addListSelectionListener((ListSelectionEvent e) -> {
            mediator.selectAllContacts();
        });
        mediator.registerAllContactsList(allContactsList);
        allContactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var invitedContactsList = new JList();
        invitedContactsList.setModel(new InvitedContactsModel());
        invitedContactsList
                .addListSelectionListener((ListSelectionEvent e) -> {
                    mediator.selectInvitedContacts();
                });
        mediator.registerInvitedContactsList(invitedContactsList);
        invitedContactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var pnlViews = new PanelViews(allContactsList, invitedContactsList);
        frmMain.add(pnlViews, BorderLayout.CENTER);

        frmMain.setSize(500, 400);
        frmMain.setLocationRelativeTo(null);
        frmMain.setVisible(true);
    }
}
