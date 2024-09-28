package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.delegate;

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

import me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.graphics.Diagram;
import me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.graphics.GraphicIF;
import me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.graphics.Relation;
import me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.prototype.PrototypeManager;

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
public class PanelCanvas extends JPanel implements Serializable {

    /**
     * The diagram itself
     */
    private Diagram diagram;
    /**
     * A next graphic, could be either circle or line
     */
    private GraphicIF nextGraphic;
    /**
     * The position of the mouse pointer. Relevant for selections
     */
    private Point mousePosition;
    /**
     * Start of a line
     */
    private Point lineStart;
    /**
     * End of a line
     */
    private Point lineEnd;
    /**
     * The selected graphical object
     */
    private GraphicIF selected;
    /**
     * Flag, if there's a line or a circle to be drawn
     */
    private boolean createLine = false;
    /**
     * A line
     */
    private Line2D tempLine;
    /**
     * A group of radio buttons
     */
    private final ButtonGroup group = new ButtonGroup();
    /**
     * The action for selecting "Create Line"
     */
    private final Action createLineAction = new AbstractAction("Create line") {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextGraphic = null;
            createLine = true;
        }
    };
    /**
     * The menu entry for "Create Line"
     */
    private final JCheckBoxMenuItem mnCreateLine = new JCheckBoxMenuItem(createLineAction);
    /**
     * The action for "select graphica"
     */
    private final Action selectAction = new AbstractAction("Select a graphic") {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextGraphic = null;
            createLine = false;
        }
    };
    /**
     * The menu entry for "select graphic"
     */
    private final JCheckBoxMenuItem mnSelect = new JCheckBoxMenuItem(selectAction);
    /**
     * A submenu for selecting the color of the circle
     */
    private final JMenu mnCirclePrototypes = new JMenu("Choose circle");
    /**
     * Handle mouse events
     */
    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        /**
         * Drag
         *
         * @param e the event
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (selected != null) {
                // Move an object to the new position and redraw
                selected.moveTo(e.getPoint());
                repaint();
            } else if (createLine && lineStart != null) {
                // Draw a line and redraw everything
                // while moving along with the mouse pointer
                var recentPoint = e.getPoint();
                tempLine = new Line2D.Double(recentPoint.getX(), recentPoint.
                        getY(), lineStart.getX(), lineStart.getY());
                repaint();
            }
        }

        /**
         * On click
         *
         * @param event the event
         */
        @Override
        public void mouseClicked(MouseEvent event) {
            mousePosition = event.getPoint();
            if (nextGraphic != null)
                try {
                // Add a circle to the diagram
                var newGraphic = (GraphicIF) nextGraphic.clone();
                selected = newGraphic;
                diagram.add(newGraphic, mousePosition);
            } catch (CloneNotSupportedException ex) {
                new ErrorDialog(ex);
            } else
                // Look for an object at mouse position and select it
                selected = diagram.findGraphic(mousePosition);
            repaint();
        }

        /**
         * On press (and also on press and hold)
         *
         * @param e the event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            var mousePosition = e.getPoint();
            if (createLine) {
                // Start a new line
                lineStart = e.getPoint();
                repaint();
            } else
                // Select an object near mouse position
                selected = diagram.findGraphic(mousePosition);
        }

        /**
         * On mouse button release
         *
         * @param e the event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            tempLine = null;
            if (createLine) {
                // Draw a line only if there's a circle at every end
                lineEnd = e.getPoint();
                var start = diagram.findGraphic(lineStart);
                var end = diagram.findGraphic(lineEnd);
                if (start != null && end != null && !(start == end)) {
                    var line = new Relation(start, end);
                    diagram.addRelation(line);
                }
                repaint();
            }
        }
    };

    /**
     * Constructor. Gets a diagram and builds up context menu with the assigned
     * actions
     *
     * @param diagram
     */
    public PanelCanvas(Diagram diagram) {
        this.diagram = diagram;
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
     * Panel constructor calls the other constructor with a new diagram
     */
    public PanelCanvas() {
        this(new Diagram());
    }

    /**
     * Set current diagram to new version
     *
     * @param clone the other diagram
     */
    public void setDiagram(Diagram clone) {
        this.diagram = clone;
        repaint();
    }

    /**
     * Returns a deep copy of the diagram
     *
     * @return Clone of the diagram
     */
    public Diagram getDiagramAsClone() {
        Diagram clone = null;
        try {
            this.nextGraphic = null;
            this.selected = null;
            this.createLine = false;
            ObjectOutputStream oos;
            ByteArrayInputStream bais;
            ObjectInputStream ois;
            try ( var baos = new ByteArrayOutputStream()) {
                oos = new ObjectOutputStream(baos);
                oos.writeObject(diagram);
                bais = new ByteArrayInputStream(baos.toByteArray());
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
     * Add a circle to the context menu
     *
     * @param prototype the circle
     */
    private void addPrototype(final GraphicIF prototype) {
        final var drawAction = new AbstractAction() {
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
            diagram.deleteSelectedGraphic(selected);
            selected = null;
            repaint();
        }
    }

    /**
     * Draw the diagram and any temporary line
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
