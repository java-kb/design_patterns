package me.study.designpatterns.behavioral.command.gui.example02_swing_drawing_undo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Command"
 */
public class GUI extends JFrame {

    /**
     * The look of a command list, the undo list as well as the redo list
     */
    private class MyListPanel extends JPanel {

        /**
         * The list part
         */
        final JList list = new JList();

        /**
         * Constructor: Place the header and connect to the list
         *
         * @param label Header
         * @param model list of commands
         */
        @SuppressWarnings("unchecked")
        MyListPanel(String label, ListModel model) {
            this.setBackground(Color.WHITE);
            var lblHeader = new JLabel(label);
            var paddingBorder = BorderFactory
                    .createEmptyBorder(10, 10, 10, 10);

            lblHeader.setBorder(BorderFactory
                    .createCompoundBorder(null, paddingBorder));

            this.setLayout(new BorderLayout());
            this.add("North", lblHeader);
            this.add("Center", list);
            list.setModel(model);
        }
    }
    /**
     * The list of commands which can be undone
     */
    private final CommandHolder undoCommands;
    /**
     * The list of commands which can be redone
     */
    private final CommandHolder redoCommands;
    /**
     * The command to draw a red line
     */
    private AbstractDrawCommand cmdRed;
    /**
     * The command to draw a blue line
     */
    private AbstractDrawCommand cmdBlue;
    /**
     * An array of the two commands
     */
    private AbstractDrawCommand[] allCommands;

    /**
     * Create the GUI
     */
    public GUI() {
        this.redoCommands = new CommandHolder();
        this.undoCommands = new CommandHolder();
        this.cmdBlue = new CommandBlue(undoCommands, redoCommands);
        this.cmdRed = new CommandRed(undoCommands, redoCommands);
        this.allCommands = new AbstractDrawCommand[]{
            cmdRed, cmdBlue
        };
        // Create buttons
        final var btnRed = new JButton("Red");
        final var btnBlue = new JButton("Blue");
        final var btnUndo = new JButton("Undo");
        final var btnRedo = new JButton("Redo");

        // Create main panel, draw panel and footer panel
        final var main = new JPanel();
        main.setLayout(new BorderLayout());
        final var canvas = new JPanel() {
            // The paint method clears the canvas with a white background
            // and then draws all blue and red lines
            @Override
            public void paintComponent(Graphics graphics) {
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                for (var tempCommand : allCommands)
                    tempCommand.draw(graphics, this.getWidth(), this.getHeight());
            }
        };
        JPanel left = new JPanel();
        left.setBorder(new LineBorder(Color.BLACK));
        left.setSize(320, 600);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        final var footer = new JPanel();
        footer.add(btnRed);
        footer.add(btnUndo);
        footer.add(btnRedo);
        footer.add(btnBlue);
        left.add("South", footer);
        main.add("Center", canvas);
        main.add("West",left);
        // Create undo-list and redo-list
        final var undoList = new MyListPanel("List of commands", undoCommands);
        left.add("North", undoList);

        final var redoList = new MyListPanel("List of deleted commands", redoCommands);
        left.add("North", redoList);

        // Red: Create a new red line and redraw the canvas
        btnRed.addActionListener((var evt) -> {
            cmdRed.execute();
            canvas.repaint();
        });

        // Blue: Create a new blue line and redraw the canvas
        btnBlue.addActionListener((var evt) -> {
            cmdBlue.execute();
            canvas.repaint();
        });

        // UNDO: remove the last (or the selected) element from the undo list,
        // undo the command and then redraw the canvas
        btnUndo.addActionListener((var evt) -> {
            var maxIndex = undoList.list.getLastVisibleIndex();
            if (maxIndex >= 0) {
                var selIndex = undoList.list.getSelectedIndex();
                if (selIndex >= 0) {
                    maxIndex = selIndex;
                    undoList.list.clearSelection();
                }
                var command = (AbstractDrawCommand) undoList.list
                        .getModel().getElementAt(maxIndex);
                command.undo();
                canvas.repaint();
            }
        });

        // REDO: remove the last (or selected) element from the redo list,
        // repeat this command and then redraw the canvas
        btnRedo.addActionListener((var evt) -> {
            var maxIndex = redoList.list.getLastVisibleIndex();
            if (maxIndex >= 0) {
                var selIndex = redoList.list.getSelectedIndex();
                if (selIndex >= 0) {
                    maxIndex = selIndex;
                    redoList.list.clearSelection();
                }
                var command = (AbstractDrawCommand) redoList.list
                        .getModel().getElementAt(maxIndex);
                command.redo();
                canvas.repaint();
            }
        });

        this.getContentPane().add(main);
        this.setTitle("Command Demo");
        this.setLocationRelativeTo(null);
        this.setSize(1024, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Create the new GUI
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        new GUI();
    }

}
