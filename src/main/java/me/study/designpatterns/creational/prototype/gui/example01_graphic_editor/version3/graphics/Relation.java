package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version3.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
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
public class Relation implements RelationIF {

    /**
     * The first element of a relation
     */
    private final GraphicIF start;
    /**
     * The second element of a relation
     */
    private final GraphicIF end;

    /**
     * Create a relation between two elements of the diagram
     *
     * @param start first element
     * @param end second element
     */
    public Relation(GraphicIF start, GraphicIF end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Copy a line
     *
     * @return the copy
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException exception) {
            return new AssertionError("Clone not supported" + exception.
                    getMessage());
        }
    }

    /**
     * If a circle with a relation is deleted, also delete the circle on the
     * other end of the line
     *
     * @param deleted a set of deleted objects
     *
     * @return true, if one of the objects is part of a relation
     */
    @Override
    public boolean shouldBeDeleted(Set<GraphicIF> deleted) {
        return deleted.contains(start) || deleted.contains(end);
    }

    /**
     * Deliver the starting circle of a line
     *
     * @return a circle
     */
    @Override
    public GraphicIF getStart() {
        return start;
    }

    /**
     * Deliver the ending circle of a line
     *
     * @return a circle
     */
    @Override
    public GraphicIF getEnd() {
        return end;
    }

    /**
     * Determine the bounding box around the line
     *
     * @return a rectangle
     */
    @Override
    public Rectangle2D getSurroundingRectangle() {
        var line = getRelationLine();
        var rectangle = new Rectangle2D.Double();
        rectangle.
                setFrameFromDiagonal(line.getX1(), line.getY1(), line.getX2(), line.
                        getY2());
        return rectangle;
    }

    /**
     * Draw the line
     *
     * @param graphicContext the context for drawing
     */
    @Override
    public void draw(Graphics2D graphicContext) {
        graphicContext.draw(getRelationLine());
    }

    /**
     * Draw the box which surrounds a line
     *
     * @param graphicsContext the context for drawing
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
     * Check, if a point is close enough to a line (the "hit box")
     *
     * @param point
     *
     * @return true, if the point is close enough
     */
    @Override
    public boolean contains(Point2D point) {
        return getRelationLine().ptSegDist(point) < 3;
    }

    /**
     * Determine a line between two circles
     *
     * @return the line object
     */
    @Override
    public Line2D getRelationLine() {
        var startPoint = start.getSurroundingRectangle();
        var endPoint = end.getSurroundingRectangle();
        var startCenter = new Point2D.Double(startPoint.getCenterX(), startPoint.
                getCenterY());
        var endCenter = new Point2D.Double(endPoint.getCenterX(), endPoint.
                getCenterY());
        return new Line2D.Double(start.getAnchorPoint(endCenter), end.
                getAnchorPoint(startCenter));
    }

    /**
     * Moving a point along the line is not implemented here
     *
     * @param point
     */
    @Override
    public void moveTo(Point point) {
    }

    /**
     * The "anchor" of a line is its center
     *
     * @param point just any point
     *
     * @return the center of the line
     */
    @Override
    public Point2D getAnchorPoint(Point2D point) {
        var line = getRelationLine();
        double x = (line.getX2() + line.getX1()) / 2.0;
        double y = (line.getY2() + line.getY1()) / 2.0;
        return new Point2D.Double(x, y);
    }

    /**
     * The icon of a line is a small line
     *
     * @return the icon
     */
    @Override
    public Icon getIcon() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.drawLine(40, 20, 20, 0);
            }

            @Override
            public int getIconWidth() {
                return 20;
            }

            @Override
            public int getIconHeight() {
                return 20;
            }
        };
    }

    /**
     * The description of a line
     *
     * @return text
     */
    @Override
    public String toString() {
        return "A line connecting \"" + start + "\" and \"" + end + "\"";
    }
}
