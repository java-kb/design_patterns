package me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import me.study.designpatterns.creational.prototype.gui.example01_graphic_editor.version2.delegate.PanelCanvas;

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
public class AppStart {

    /**
     * The graphical frame of the application
     */
    private final JFrame myFrame = new JFrame();
    /**
     * The action for exiting the application
     */
    private final Action exitAction = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    /**
     * The action for deleting an object
     */
    private final Action deleteAction = new AbstractAction("Delete selected") {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.deleteSelectedGraphic();
        }
    };
    /**
     * The action for cloning a complete diagram
     */
    private final Action cloneAction = new AbstractAction("Clone Diagram") {
        @Override
        public void actionPerformed(ActionEvent e) {
            var clone = canvas.getDiagramAsClone();
            cloneCanvas.setDiagram(clone);
        }
    };
    /**
     * The canvas
     */
    private final PanelCanvas canvas = new PanelCanvas();
    /**
     * A second canvas for the clone
     */
    private final PanelCanvas cloneCanvas = new PanelCanvas();

    /**
     * Put everything together, assign the actions and make it visible
     */
    private void start() {
        // Set up menus
        var mnMenubar = new JMenuBar();
        var mnFile = new JMenu("File");
        var mnEdit = new JMenu("Edit ...");
        mnMenubar.add(mnFile);
        mnMenubar.add(mnEdit);
        mnFile.add(new JMenuItem(cloneAction));
        mnFile.add(new JMenuItem(exitAction));
        mnEdit.add(new JMenuItem(deleteAction));

        // Set up frame
        myFrame.setSize(600, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        var splMain = new JSplitPane();
        splMain.setResizeWeight(0.5);
        splMain.setLeftComponent(new JScrollPane(canvas));
        splMain.setRightComponent(new JScrollPane(cloneCanvas));
        myFrame.add(splMain, BorderLayout.CENTER);

        myFrame.setJMenuBar(mnMenubar);
        myFrame.setVisible(true);
    }

    
    /**
     * Create application and call the start method
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        (new AppStart()).start();
    }
}
