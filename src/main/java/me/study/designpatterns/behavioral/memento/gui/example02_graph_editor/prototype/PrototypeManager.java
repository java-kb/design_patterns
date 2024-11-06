package me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.prototype;

import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.Circle;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.GraphicIF;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class PrototypeManager implements Serializable {

    /**
     * A list of all graphic objects
     */
    private static final List<GraphicIF> prototypes = new ArrayList<>();

    /**
     * In advance, create a set of colored circles and add them to the list
     */
    static {
        prototypes.add(new Circle(Color.WHITE));
        prototypes.add(new Circle(Color.BLUE));
        prototypes.add(new Circle(Color.GREEN));
        prototypes.add(new Circle(Color.RED));
        prototypes.add(new Circle(Color.YELLOW));
        prototypes.add(new Circle(Color.BLACK));
    }

    /**
     * Convert the list into an array
     *
     * @return an array of the prototypes
     */
    public static GraphicIF[] getPrototypes() {
        var result = new GraphicIF[prototypes.size()];
        for (var i = 0; i < prototypes.size(); i++)
            result[i] = prototypes.get(i);
        return result;
    }
}
