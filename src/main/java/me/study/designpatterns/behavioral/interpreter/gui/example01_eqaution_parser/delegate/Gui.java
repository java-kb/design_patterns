package me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.delegate;

import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.Parser;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Interpreter"
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Consructor. Initializes the parts
     */
    @SuppressWarnings("unchecked")
    public Gui() {
        initComponents();
        final var expression = new String[]{
            "5;",
            " 5 + 5; ",
            " 1  +   5 +4; ",
            " 15 - 5;",
            " 20 - 3 - 7 ;",
            "5-15+20;",
            "20 - 15 +5;",
            "5*2;",
            "5*1 + 5;",
            "20 / 2 + 5 - 10 +5;",
            "-3+13;",
            "-2*-5;"
        };
        @SuppressWarnings("unchecked")
        final var boxModel = new DefaultComboBoxModel(expression);
        this.cmbExpression.setModel(boxModel);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblExpression = new javax.swing.JLabel();
        cmbExpression = new javax.swing.JComboBox();
        btnStart = new javax.swing.JButton();
        lblScan = new javax.swing.JLabel();
        edtScan = new javax.swing.JTextField();
        lblResult = new javax.swing.JLabel();
        edtResult = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interpreter Demo");

        lblExpression.setText("Calculate:");
        lblExpression.setToolTipText("");

        cmbExpression.setEditable(true);
        cmbExpression.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        lblScan.setText("Scanned:");

        edtScan.setEditable(false);

        lblResult.setText("Result:");

        edtResult.setEditable(false);

        btnExit.setText("Quit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(cmbExpression, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblExpression)
                                    .addComponent(btnStart)
                                    .addComponent(lblScan))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(edtScan))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(edtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblResult)
                            .addComponent(btnExit))
                        .addGap(0, 160, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExpression)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbExpression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edtScan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblResult)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edtResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evaluates the input and gets it calculated
     *
     * @param evt the triggering event
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnStartActionPerformed
    {//GEN-HEADEREND:event_btnStartActionPerformed
        final var input = (String) cmbExpression.getSelectedItem();
        try {
            var scanner = new Scanner(input);
            edtScan.setText(scanner.toString());
            final var symbols = scanner.getSymbols();
            var parser = new Parser(symbols);
            var result = parser.getRoot();
            System.out.println(result);
            final var outcome = result.calc();
            edtResult.setText(Double.toString(outcome));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * Leave the program
     *
     * @param evt the triggering event
     */
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExitActionPerformed
    {//GEN-HEADEREND:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * Delivers the GUI for execution
     *
     * @param args are ignored
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox cmbExpression;
    private javax.swing.JTextField edtResult;
    private javax.swing.JTextField edtScan;
    private javax.swing.JLabel lblExpression;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblScan;
    // End of variables declaration//GEN-END:variables
}
