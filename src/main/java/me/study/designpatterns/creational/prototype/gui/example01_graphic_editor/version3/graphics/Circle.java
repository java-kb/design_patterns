package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version3.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import javax.swing.Icon;

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
public class Circle implements GraphicIF {

    /**
     * The horizontal position of a circle's center
     */
    private double x;
    /**
     * The vertical position of a circle's center
     */
    private double y;
    /**
     * The size of the circle
     */
    private final double size;
    /**
     * The color of the circle
     */
    private final Color color;
    /**
     * We start with a standard size
     */
    private final int defaultSize = 20;

    /**
     * The constructor gets the color and sets some default values
     *
     * @param color of the circle
     */
    public Circle(Color color) {
        size = defaultSize;
        x = 0;
        y = 0;
        this.color = color;
    }

    /**
     * For cloning, a shallow copy is sufficient in this context
     *
     * @return the cloned object
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException exception) {
            return new AssertionError("Clone not succesful" + exception.
                    getMessage());
        }
    }

    /**
     * A circle doesn't have to be deleted if another circle is deleted
     *
     * @param deleted a set of deleted objects
     *
     * @return always false in this case
     */
    @Override
    public boolean shouldBeDeleted(Set<GraphicIF> deleted) {
        return false;
    }

    /**
     * Paint the filled circle
     *
     * @param graphicsContext the graphics context
     */
    @Override
    public void draw(Graphics2D graphicsContext) {
        var circle = new Ellipse2D.Double(x, y, size, size);
        var tempColor = graphicsContext.getColor();
        graphicsContext.setColor(color);
        graphicsContext.fill(circle);
        graphicsContext.setColor(tempColor);
        graphicsContext.draw(circle);
    }

    /**
     * Draw a bounding box around a circle
     *
     * @param graphicsContext the graphic context
     */
    @Override
    public void drawSurroundingRectangle(Graphics2D graphicsContext) {
        var surroundingRectangle = this.getSurroundingRectangle();
        var color = graphicsContext.getColor();
        graphicsContext.setColor(Color.PINK);
        graphicsContext.draw(surroundingRectangle);
        graphicsContext.setColor(color);
    }

    /**
     * Move circle to another position
     *
     * @param point the new center
     */
    @Override
    public void moveTo(Point point) {
        x = point.getX();
        y = point.getY();
    }

    /**
     * Check, if a given point (selected with the mouse) is somewhere inside the
     * circle or not * @param point the given point
     *
     * @param point to compare with
     *
     * @return true, if point is within circle
     */
    @Override
    public boolean contains(Point2D point) {
        var circle = new Ellipse2D.Double(x, y, size, size);
        return circle.contains(point);
    }

    /**
     * Determine a bounding rectangle for the circle
     *
     * @return the rectangle
     */
    @Override
    public Rectangle2D getSurroundingRectangle() {
        return new Rectangle2D.Double(x, y, size, size);
    }

    /**
     * Calculate the coordinates of line connecting two circles. Start and end
     * are exactly on the circumference of the connected circles
     *
     * @param point to draw the line to
     *
     * @return point on the edge of the circle, next to the given point
     */
    @Override
    public Point2D getAnchorPoint(Point2D point) {
        // Center of circle
        var centerX = x + size / 2;
        var centerY = y + size / 2;
        // Distance in x-direction
        var dx = point.getX() - centerX;
        // Distance in y-direction
        var dy = point.getY() - centerY;
        // Diagonal distance
        var distance = Math.sqrt(dx * dx + dy * dy);

        var anchorPoint = point;
        if (distance > 0.0) {
            var anchorX = centerX + dx * (size / 2) / distance;
            var anchorY = centerY + dy * (size / 2) / distance;
            anchorPoint = new Point2D.Double(anchorX, anchorY);
        }
        return anchorPoint;
    }

    /**
     * The icon of a circle also is a (small) circle
     *
     * @return circle as icon
     */
    @Override
    public Icon getIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Circle.this.draw((Graphics2D) g);
            }

            @Override
            public int getIconWidth() {
                var width = Circle.this.getSurroundingRectangle().getWidth();
                return (int) (width + 1);
            }

            @Override
            public int getIconHeight() {
                var height = Circle.this.getSurroundingRectangle().
                        getHeight();
                return (int) (height + 1);
            }
        };
    }

    /**
     * The properties of the circle
     *
     * @return text
     */
    @Override
    public String toString() {
        return "A Circle at " + x + " : " + y;
    }
}
