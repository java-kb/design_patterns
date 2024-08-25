package me.study.designpatterns.behavioral.state.gui.example3_painting;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TestApp {
    public static void main(String[] args) {
        Painter painter = new Painter();
        UI ui = new UI(painter);
        ui.init();
    }

}

class Painter {
    private Tool tool;

    public Painter() {
        // this.state = new ReadyState(this);

    }

    void setZoomTool() {
        this.tool = new ZoomTool(this);
    }

    void setPaintTool() {
        this.tool = new PaintTool(this);
    }

    void HandleMouseDown(int x, int y) {
        this.tool.HandleMouseDown(x, y);
    }

    void HandleMouseMove(int x, int y) {
        this.tool.HandleMouseMove(x, y);
    }

    void HandleMouseUp(int x, int y) {
        this.tool.HandleMouseUp(x, y);
    }
}

abstract class Tool {
    Painter painter;

    Tool(Painter painter) {
        this.painter = painter;
    }

    public abstract void HandleMouseDown(int x, int y);

    public abstract void HandleMouseMove(int x, int y);

    public abstract void HandleMouseUp(int x, int y);
}

class ZoomTool extends Tool {

    ZoomTool(Painter painter) {
        super(painter);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void HandleMouseDown(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseDown'");
    }

    @Override
    public void HandleMouseMove(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseMove'");
    }

    @Override
    public void HandleMouseUp(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseUp'");
    }

}

class PaintTool extends Tool {

    PaintTool(Painter painter) {
        super(painter);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void HandleMouseDown(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseDown'");
    }

    @Override
    public void HandleMouseMove(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseMove'");
    }

    @Override
    public void HandleMouseUp(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HandleMouseUp'");
    }

}

class UI {
    private Painter painter;
    private static JPanel textField = new JPanel();

    public UI(Painter painter) {
        this.painter = painter;
    }

    public void init() {
        JFrame frame = new JFrame("Test Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel context = new JPanel();
        context.setLayout(new BorderLayout());
        frame.getContentPane().add(context);
        
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JPanel canvas = new JPanel();
        Color color = Color.WHITE;
        canvas.setForeground(color);
        canvas.setBorder(new LineBorder(Color.BLACK));
        canvas.setSize(context.getWidth(), 400);
        
        context.add(buttons, BorderLayout.NORTH);
        
        context.add(canvas, BorderLayout.CENTER);
        context.add(textField, BorderLayout.SOUTH);
        canvas.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }
            
        });
        boolean dragstarted=false;
        canvas.addMouseMotionListener(new MouseMotionListener()  {

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
            }

        });
        // Context delegates handling user's input to a state object. Naturally,
        // the outcome will depend on what state is currently active, since all
        // states can handle the input differently.
        JButton zoom = new JButton("Zoom");
        zoom.addActionListener(e -> painter.setZoomTool());
        JButton paint = new JButton("Paint");
        paint.addActionListener(e -> painter.setPaintTool());

        frame.setVisible(true);
        frame.setSize(800, 600);
        buttons.add(zoom);
        buttons.add(paint);
    }
}