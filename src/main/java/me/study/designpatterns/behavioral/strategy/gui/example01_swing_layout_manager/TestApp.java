package me.study.designpatterns.behavioral.strategy.gui.example01_swing_layout_manager;

import java.awt.*;
import javax.swing.*;

public class TestApp{
    
    /**
     * Create a panel and use the new LayoutManager, add some components and
     * display everything
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var frmMain = new JFrame("Demo TwoColumnLayout");
        var pnlTest = new JPanel();
        pnlTest.setLayout(new TwoColumnLayout(10, 5));

        pnlTest.add(new JLabel("Surname: "));
        pnlTest.add(new JTextField("<Enter>"));
        pnlTest.add(new JLabel("Forename: "));
        pnlTest.add(new JTextField("<Enter>"));
        pnlTest.add(new JLabel("date of birth: "));
        pnlTest.add(new JTextField("<Enter>"));
        pnlTest.add(new JLabel("Adress: "));
        pnlTest.add(new JTextField("<Enter>"));
        pnlTest.add(new JButton("Send"));

        frmMain.setContentPane(pnlTest);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frmMain.pack();
        frmMain.setVisible(true);
    }
}
/**
 * LayoutManager, der die übergebenen Komponenten in zwei Spalten sortiert.
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Strategy"
 */
 class TwoColumnLayout implements LayoutManager {

    /**
     * Preferred width of left column
     */
    private int preferredWidthLeft;
    /**
     * Preferred width of right column
     */
    private int preferredWidthRight;
    /**
     * Preferred height
     */
    private int preferredHeight;
    /**
     * Min width
     */
    private int minWidth;
    /**
     * Min height
     */
    private int minHeight;

    /**
     * Vertical and horizontal gap
     */
    private final int vgap;
    private final int hgap;

    /**
     * The constructor takes two distances as prescriptions
     *
     * @param hgap horizontal distance between columns
     * @param vgap vertical distance between lines
     */
    TwoColumnLayout(int hgap, int vgap) {
        this.vgap = vgap;
        this.hgap = hgap;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        //
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        //
    }

    /**
     * Put the components into the two columns alternating left and right
     *
     * @param container der Container für die Darstellung
     */
    @Override
    public void layoutContainer(Container container) {
        int componentCount = container.getComponentCount();
        if (componentCount > 0) {
            calcSizes(container);
            var components = container.getComponents();
            int leftColumn = hgap;
            int tightColumn = 2 * hgap + preferredWidthLeft;
            int left, top = vgap, width = 0, height = vgap;

            for (var i = 0; i < componentCount; i++) {
                var component = components[i];
                if (component.isVisible()) {
                    width = component.getPreferredSize().width;
                    height = component.getPreferredSize().height;
                    if ((i % 2) == 0) {
                        // Left column
                        if (i > 0)
                            top += height + vgap;
                        left = leftColumn;
                    } else {
                        // Right column
                        left = tightColumn;
                        width = (container.getSize().width) - vgap - left;
                    }
                    // System.out.println("left: " + left + " top: " + top + " size: " + width + " x " + height);
                    component.setBounds(left, top, width, height);
                }
            }
        }
    }

    /**
     * Calculate all relevant figures for the display
     *
     * @param container the container
     */
    private void calcSizes(Container container) {
        minWidth = 2 * hgap;
        minHeight = 2 * vgap;
        preferredWidthLeft = minWidth;
        preferredWidthRight = minWidth;
        preferredHeight = minHeight;
        var componentCount = container.getComponentCount();
        if (componentCount > 0)
            for (var i = 0; i < componentCount; i++) {
                var tempComponent = container.getComponent(i);
                var dimension = tempComponent.getPreferredSize();
                preferredHeight += dimension.height;
                var componentWidth = dimension.width;
                if ((i % 2) == 0)
                    // left column
                    preferredWidthLeft = Math.
                            max(componentWidth, preferredWidthLeft);
                else
                    // right column
                    preferredWidthRight = Math.
                            max(componentWidth, preferredWidthRight);
            }
    }

    /**
     * Get preferred dimensions
     *
     * @param container the container
     *
     * @return preferred dimension
     */
    @Override
    public Dimension preferredLayoutSize(Container container) {
        calcSizes(container);
        var dimension = new Dimension(0, 0);
        dimension.width = preferredWidthRight + preferredWidthLeft;
        dimension.height = preferredHeight;
        return dimension;
    }

    /**
     * If necessary: Fix minimum dimension
     *
     * @param container the container
     *
     * @return minimum sizes
     */
    @Override
    public Dimension minimumLayoutSize(Container container) {
        calcSizes(container);
        var dimension = new Dimension(0, 0);
        dimension.width = minWidth;
        dimension.height = minHeight;
        return dimension;
    }

    @Override
    public String toString() {
        return "TwoColumnLayout";
    }

}
