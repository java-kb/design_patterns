package me.study.designpatterns.behavioral.memento.gui.example02_graph_editor;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.delegate.PanelCanvas;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.graphics.Diagram;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.memento.Stack;

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
public class ApplStart {

    /**
     * A stack for all the clones of the diagram
     */
    private final Stack<Diagram> caretaker = new Stack<>();

    /**
     * The frame for the application
     */
    private final JFrame myFrame = new JFrame();

    /**
     * The action for "Exit"
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
     * The undo action gets a memento from the stack and restores it as a
     * diagram
     */
    private final Action undoAction = new AbstractAction("Undo") {
        @Override
        public void actionPerformed(ActionEvent e) {
            myFrame.setTitle("Memento Demo");
            if (!caretaker.empty()) {
                var diagram = caretaker.pop();
                canvas.setDiagram(diagram);
            } else
                myFrame.setTitle("Undo not possible - Stack is empty");
        }
    };

    /**
     * The canvas
     */
    private final PanelCanvas canvas = new PanelCanvas(caretaker);

    /**
     * Specify application frame, assign actions, then make everything visible
     */
    private void start() {
        // Set up menus
        var mnMenubar = new JMenuBar();
        var mnFile = new JMenu("File");
        var mnEdit = new JMenu("Edit ...");
        mnMenubar.add(mnFile);
        mnMenubar.add(mnEdit);
        mnEdit.add(new JMenuItem(deleteAction));
        mnEdit.add(new JMenuItem(undoAction));
        mnFile.add(new JMenuItem(exitAction));

        // Set up frame
        myFrame.setSize(600, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.add(new JScrollPane(canvas), BorderLayout.CENTER);

        myFrame.setJMenuBar(mnMenubar);
        myFrame.setVisible(true);
    }

    /**
     * Create application and call start method
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        (new ApplStart()).start();
    }
}
