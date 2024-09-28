package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version1.graphics;

import java.awt.geom.Line2D;

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
public interface RelationIF extends GraphicIF {

    /**
     * Returns the starting graphic
     *
     * @return The starting graphic
     */
    GraphicIF getStart();

    /**
     * Returns the ending graphic
     *
     * @return The ending graphic
     */
    GraphicIF getEnd();

    /**
     * Returns a line connecting two graphics
     *
     * @return A line joining two graphics
     */
    Line2D getRelationLine();
}
