package me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.delegate;

import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.Diagram;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.GraphicIF;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.Relation;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.RelationIF;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.memento.Stack;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.prototype.PrototypeManager;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Memento"
 */
public class PanelCanvas extends JPanel implements Serializable {

    /**
     * The diagram
     */
    private Diagram diagram;
    /**
     * The next graphic to draw
     */
    private GraphicIF nextGraphic;
    /**
     * The position of the mouse pointer (important for selecting)
     */
    private Point mousePosition;
    /**
     * Starting point of a line
     */
    private Point lineStart;
    /**
     * Ending point of a line
     */
    private Point lineEnd;
    /**
     * The current graphics object
     */
    private GraphicIF selected;
    /**
     * True, if lines will be drawn,
     */
    private boolean createLine = false;
    /**
     * A line
     */
    private Line2D tempLine;
    /**
     * A stack for the undo option
     */
    private final Stack caretaker;
    /**
     * A group of buttons
     */
    private final ButtonGroup group = new ButtonGroup();
    /**
     * The action for creating a line
     */
    private final Action createLineAction = new AbstractAction("Create line") {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextGraphic = null;
            createLine = true;
        }
    };
    /**
     * The menu entry for the "create line" action
     */
    private final JCheckBoxMenuItem mnCreateLine = new JCheckBoxMenuItem(createLineAction);
    /**
     * The action "select a graphics object"
     */
    private final Action selectAction = new AbstractAction("Select a graphic") {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextGraphic = null;
            createLine = false;
        }
    };
    /**
     * The menu entry for the "select a graphic" action
     */
    private final JCheckBoxMenuItem mnSelect = new JCheckBoxMenuItem(selectAction);
    /**
     * A sub menu for the differently colored circles
     */
    private final JMenu mnCirclePrototypes = new JMenu("Choose circle");
    /**
     * Handle mouse actions
     */
    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        /**
         * On dragging with pressed mouse button
         *
         * @param e the mouse event
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (selected != null) {
                // Move an object and redraw everything
                selected.moveTo(e.getPoint());
                repaint();
            } else if (createLine && lineStart != null) {
                // Draw a new line and move it along with the mouse pointer
                var recentPoint = e.getPoint();
                tempLine = new Line2D.Double(recentPoint.getX(), recentPoint.
                        getY(), lineStart.getX(), lineStart.getY());
                repaint();
            }
        }

        /**
         * On click
         *
         * @param event the mouse event
         */
        @Override
        public void mouseClicked(MouseEvent event) {
            mousePosition = event.getPoint();
            if (nextGraphic != null)
                try {
                // Create a circle and add it to the diagram
                var newGraphic = (GraphicIF) nextGraphic.clone();
                selected = newGraphic;
                diagram.add(newGraphic, mousePosition);
            } catch (CloneNotSupportedException ex) {
                new ErrorDialog(ex);
            } else
                // Select object on mouse position
                selected = diagram.findGraphic(mousePosition);
            repaint();
        }

        /**
         * On click (and hold)
         *
         * @param e the mouse event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            var mousePosition = e.getPoint();
            if (createLine) {
                // Start a new line
                lineStart = e.getPoint();
                repaint();
            } else
                // Select object on mouse position
                selected = diagram.findGraphic(mousePosition);
        }

        /**
         * On releasing the mouse button
         *
         * @param e the mouse event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            tempLine = null;
            if (createLine) {
                // Draw a line, if there's a circle on both ends
                lineEnd = e.getPoint();
                GraphicIF start = diagram.findGraphic(lineStart);
                GraphicIF end = diagram.findGraphic(lineEnd);
                if (start != null && end != null && !(start == end)) {
                    RelationIF line = new Relation(start, end);
                    diagram.addRelation(line);
                }
                repaint();
            }
        }
    };

    /**
     * Constructor. Get a diagram, build the context menu and assign mouse
     * actions
     *
     * @param diagram
     * @param caretaker the stack caring for the undos
     */
    public PanelCanvas(Diagram diagram, Stack caretaker) {
        this.diagram = diagram;
        this.caretaker = caretaker;
        mnCreateLine.setIcon(new Relation(null, null).getIcon());
        group.add(mnCreateLine);
        group.add(mnSelect);
        mnSelect.setSelected(true);

        final var contextMenu = new JPopupMenu();
        contextMenu.add(mnCreateLine);
        contextMenu.add(mnSelect);
        contextMenu.add(mnCirclePrototypes);

        for (final GraphicIF tempGraphic : PrototypeManager.getPrototypes())
            addPrototype(tempGraphic);
        this.setComponentPopupMenu(contextMenu);
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);
    }

    /**
     * Constructor. Gets a given stack and creates the appropriate diagram
     *
     * @param caretaker the stack with the diagram
     */
    public PanelCanvas(Stack caretaker) {
        this(new Diagram(), caretaker);
    }

    /**
     * Set a diagram from a clone
     *
     * @param clone
     */
    public void setDiagram(Diagram clone) {
        selected = null;
        this.diagram = clone;
        repaint();
    }

    /**
     * Returns a deep copy of the diagram
     *
     * @return Clone of the diagram
     */
    public Diagram getMemento() {
        Diagram clone = null;
        try {
            ObjectOutputStream oos;
            ByteArrayInputStream bais;
            ObjectInputStream ois;
            try ( var baos = new ByteArrayOutputStream()) {
                oos = new ObjectOutputStream(baos);
                oos.writeObject(diagram);
                bais = new ByteArrayInputStream(baos.
                        toByteArray());
                ois = new ObjectInputStream(bais);
                clone = (Diagram) ois.readObject();
            }
            oos.close();
            bais.close();
            ois.close();
        } catch (IOException ex) {
            new ErrorDialog(ex);
        } finally {
            return clone;
        }
    }

    /**
     * Add a circle prototype to the context menu
     *
     * @param prototype der jeweilige Kreis
     */
    private void addPrototype(final GraphicIF prototype) {
        final Action drawAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                createLine = false;
                nextGraphic = prototype;
            }
        };
        var mnNewGraphic = new JCheckBoxMenuItem(drawAction);
        mnCirclePrototypes.add(mnNewGraphic);
        group.add(mnNewGraphic);
        var icon = prototype.getIcon();
        mnNewGraphic.setIconTextGap(0);
        mnNewGraphic.setIcon(icon);
    }

    /**
     * Removes the selected graphic
     */
    public void deleteSelectedGraphic() {
        if (selected != null) {
            createMemento();
            diagram.deleteSelectedGraphic(selected);
            selected = null;
            repaint();
        }
    }

    /**
     * Pushes a clone of the diagram onto the stack
     */
    @SuppressWarnings("unchecked")
    private void createMemento() {
        var tempDiagram = getMemento();
        caretaker.push(tempDiagram);
    }

    /**
     * Draw the component and a temporary line if applicable
     *
     * @param g the context
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var graphicContext = (Graphics2D) g;
        diagram.draw(graphicContext, selected);
        if (tempLine != null)
            graphicContext.draw(tempLine);
    }
}
