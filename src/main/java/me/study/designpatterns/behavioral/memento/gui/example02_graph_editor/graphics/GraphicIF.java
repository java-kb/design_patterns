package me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
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
public interface GraphicIF extends Serializable, Cloneable {

    /**
     * Draws the graphic
     *
     * @param graphicContext The graphics context
     */
    void draw(Graphics2D graphicContext);

    /**
     * Returns if a graphic has to be deleted when another graphic is deleted
     *
     * @param deleted Map of graphics that have to be deleted
     *
     * @return True if the graphic is concerned else false
     */
    boolean shouldBeDeleted(Set<GraphicIF> deleted);

    /**
     * Tests if the graphic contains the given point
     *
     * @param point Point to check
     *
     * @return True if point is inside the grahic
     */
    public boolean contains(Point2D point);

    /**
     * Returns the surrounding rectangle of the graphic
     *
     * @return The surrounding rectangle
     */
    Rectangle2D getSurroundingRectangle();

    /**
     * Moves the graphic to another point
     *
     * @param point New point
     */
    void moveTo(Point point);

    /**
     * Returns an icon representing the graphic
     *
     * @return das Symbol für das Grafik-Element
     */
    Icon getIcon();

    /**
     * Gets the best point to connect this graphic with a relation line.
     *
     * @param externalPoint2D External point where the relations line starts
     *
     * @return The anchor point
     */
    Point2D getAnchorPoint(Point2D externalPoint2D);

    /**
     * Draws the surrounding rectangle
     *
     * @param graphicsContext Graphics context
     */
    void drawSurroundingRectangle(Graphics2D graphicsContext);

    /**
     * Clone a graphics element
     *
     * @return the cloned object
     *
     * @throws CloneNotSupportedException
     */
    Object clone() throws CloneNotSupportedException;
}
