package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

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
public class TwoColumnLayout implements LayoutManager {

    /**
     * The preferred width of a left column
     */
    private int preferredWidthLeft;
    /**
     * The preferred width of a right column
     */
    private int preferredWidthRight;
    /**
     * The preferred hight of a column
     */
    private int preferredHeight;
    /**
     * The minimum width of a column
     */
    private int minWidth;
    /**
     * The minimum height of a column
     */
    private int minHeight;
    /**
     * Vertical distance
     */
    private final int vgap;
    /**
     * Horizontal distance
     */
    private final int hgap;

    /**
     * Constructor: Get the distance values
     *
     * @param hgap horizontal gap
     * @param vgap vertical gap
     */
    TwoColumnLayout(int hgap, int vgap) {
        this.vgap = vgap;
        this.hgap = hgap;
    }

    /**
     * Method needs to be implemented, but is not used here
     *
     * @param name of the component
     * @param comp the component itself
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
        //
    }

    /**
     * Method needs to be implemented, but is not used here
     *
     * @param comp the component itself
     */
    @Override
    public void removeLayoutComponent(Component comp) {
        //
    }

    /**
     * The visible components are placed alternately left and right into two
     * columns. The distance specification is adhered to
     *
     * @param container with all the components
     */
    @Override
    public void layoutContainer(Container container) {
        var numberComponents = container.getComponentCount();
        if (numberComponents > 0) {
            calculateSizes(container);
            var components = container.getComponents();
            var leftColumn = hgap;
            var rightColumn = 2 * hgap + preferredWidthLeft;
            int left, top = vgap, width = 0, height = vgap;

            for (var i = 0; i < numberComponents; i++) {
                var comp = components[i];
                if (comp.isVisible()) {
                    width = comp.getPreferredSize().width;
                    height = comp.getPreferredSize().height;
                    if ((i % 2) == 0) {
                        // Left column
                        if (i > 0)
                            top += height + vgap;
                        left = leftColumn;
                    } else {
                        // Right column
                        left = rightColumn;
                        width = (container.getSize().width) - vgap - left;
                    }
                    // System.out.println("left: " + left + " top: " + top + " Size: " + width + " x " + height);
                    comp.setBounds(left, top, width, height);
                }
            }
        }
    }

    /**
     * Calculate all distance and size values for the components of the
     * container
     *
     * @param container
     */
    private void calculateSizes(Container container) {
        minWidth = 2 * hgap;
        minHeight = 2 * vgap;
        preferredWidthLeft = minWidth;
        preferredWidthRight = minWidth;
        preferredHeight = minHeight;
        var numberComponents = container.getComponentCount();
        if (numberComponents > 0)
            for (var i = 0; i < numberComponents; i++) {
                var comp = container.getComponent(i);
                var dimension = comp.getPreferredSize();
                preferredHeight += dimension.height;
                int componentWidth = dimension.width;
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
     * Calculate the preferred size of a container
     *
     * @param container
     *
     * @return the preferred size
     */
    @Override
    public Dimension preferredLayoutSize(Container container) {
        calculateSizes(container);
        var dimension = new Dimension(0, 0);
        dimension.width = preferredWidthRight + preferredWidthLeft;
        dimension.height = preferredHeight;
        return dimension;
    }

    /**
     * Calculate the minimu size of a container
     *
     * @param container
     *
     * @return the minimum dimensions
     */
    @Override
    public Dimension minimumLayoutSize(Container container) {
        calculateSizes(container);
        var dimension = new Dimension(0, 0);
        dimension.width = minWidth;
        dimension.height = minHeight;
        return dimension;
    }

    /**
     * Just get the name of the Layout
     *
     * @return text
     */
    @Override
    public String toString() {
        return "TwoColumnLayout";
    }
}
