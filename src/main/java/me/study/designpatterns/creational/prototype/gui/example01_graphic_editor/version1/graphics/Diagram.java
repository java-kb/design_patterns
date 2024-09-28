package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version1.graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
public class Diagram implements Serializable {

    /**
     * All elements of the diagram in a list
     */
    private final List<GraphicIF> elements = new ArrayList<>();

    /**
     * Insert a new element at a specific position
     *
     * @param newGraphic the element
     * @param position and where it is located
     */
    public void add(GraphicIF newGraphic, Point position) {
        newGraphic.moveTo(position);
        elements.add(newGraphic);
    }

    /**
     * Adds a new relation line to the diagram
     *
     * @param line Relation between two graphics
     */
    public void addRelation(RelationIF line) {
        elements.add(line);
    }

    /**
     * Searches the graphic containing the given point and returns it; if no
     * graphic can be found the return value will be null
     *
     * @param point The given point
     *
     * @return The graphic containing the point or null
     */
    public GraphicIF findGraphic(Point2D point) {
        GraphicIF result = null;
        for (var graphic : elements)
            if (graphic.contains(point))
                result = graphic;
        return result;
    }

    /**
     * Draws the diagram on the canvas
     *
     * @param graphicsContext Graphics context of canvas
     * @param selected Selected graphic - can be null
     */
    public void draw(Graphics2D graphicsContext, GraphicIF selected) {
        elements.forEach((graphic) -> {
            graphic.draw(graphicsContext);
        });
        if (selected != null)
            selected.drawSurroundingRectangle(graphicsContext);
    }

    /**
     * Deletes a graphic and all related lines
     *
     * @param pGraphic Graphic to be deleted
     */
    public void deleteSelectedGraphic(GraphicIF pGraphic) {
        Set<GraphicIF> deleted = new HashSet<>();
        deleted.add(pGraphic);
        var index = elements.indexOf(pGraphic);
        elements.remove(index);
        Iterator<GraphicIF> graphics = elements.listIterator(index);

        while (graphics.hasNext()) {
            var next = graphics.next();
            if (next.shouldBeDeleted(deleted)) {
                deleted.add(next);
                graphics.remove();
            }
        }
    }

    /**
     * Deletes all graphics from diagram
     */
    public void deleteAllGraphics() {
        elements.clear();
    }

    /**
     * Printout some information to the console
     *
     * @return text
     */
    @Override
    public String toString() {
        var result = "\nDiagram:";
        for (var tempGraphic : this.elements)
            result += tempGraphic.toString() + "\n";
        return result;
    }
}
