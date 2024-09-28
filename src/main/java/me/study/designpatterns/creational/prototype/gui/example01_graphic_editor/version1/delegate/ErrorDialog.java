package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version1.delegate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Prototype"
 */
public class ErrorDialog extends javax.swing.JDialog {

    /**
     * The dialog as a tabbed pane
     */
    private final JTabbedPane tbbErrorDialog = new JTabbedPane();

    /**
     * A private constructor. Just builds the basis. The seperate contents are
     * filled in by the other constructors
     */
    private ErrorDialog() {
        super();
        this.setTitle("Error-Dialog");
        this.getContentPane().add(tbbErrorDialog, BorderLayout.CENTER);
        var btnExit = new JButton("Exit");
        btnExit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        var pnlSouth = new JPanel();
        pnlSouth.add(btnExit);
        this.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        var dimension = new Dimension(500, 400);
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var top = (screenSize.height - dimension.height) / 2;
        var left = (screenSize.width - dimension.width) / 2;
        this.setSize(dimension);
        this.setLocation(left, top);
        this.setVisible(true);
    }

    /**
     * Initializes the component
     *
     * @param throwable Throwable that has been thrown; if null, the tab for the
     * stacktrace will not be shown
     */
    public ErrorDialog(Throwable throwable) {
        this(null, throwable);
    }

    /**
     * Initializes the component
     *
     * @param message Message to display
     */
    public ErrorDialog(String message) {
        this(message, null);
    }

    /**
     * Initializes the components and copies stacktrace to clipboard
     *
     * @param message Message to display
     * @param throwable Throwable that has been thrown; if null, the tab for the
     * stacktrace will not be shown
     */
    public ErrorDialog(String message, Throwable throwable) {
        this();
        if (message != null) {
            var txtMessage = new JTextArea();
            txtMessage.setText(message);
            var scrMessage = new JScrollPane(txtMessage);
            tbbErrorDialog.add("Message", new JScrollPane(scrMessage));
        }
        if (throwable != null) {
            var txtStacktrace = new JTextArea();
            var scrStacktrace = new JScrollPane(txtStacktrace);
            tbbErrorDialog.add("Stacktrace", scrStacktrace);
            /**
             * convert stacktrace message into strings, and encapsulated in a
             * JTextArea
             */
            var stringWriter = new StringWriter();
            var printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
            var trace = stringWriter.toString();
            txtStacktrace.setText(trace);
            var clipboard = Toolkit.getDefaultToolkit().
                    getSystemClipboard();
            clipboard.setContents(new StringSelection(trace), null);
        }
        if (throwable == null && message
                == null)
            this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fehler - bitte informieren Sie den Admin!"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
