package me.study.designpatterns.behavioral.state.gui.example2_music_player;

import javax.swing.*;
import java.awt.*;

public class UI {
    private PlayerApplication player;
    private static JTextField textField = new JTextField();

    public UI(PlayerApplication app) {
        this.player = app;
    }

    public void init() {
        JFrame frame = new JFrame("Music player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel context = new JPanel();
        context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));
        frame.getContentPane().add(context);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        context.add(textField);
        context.add(buttons);

        // Context delegates handling user's input to a state object. Naturally,
        // the outcome will depend on what state is currently active, since all
        // states can handle the input differently.
        JButton play = new JButton("Play");
        // play.addActionListener(e -> textField.setText(player.getState().onPlay()));
        play.addActionListener(e -> execute(this.player, "Play"));
        JButton stop = new JButton("Stop");
        // stop.addActionListener(e -> textField.setText(player.getState().onLock()));
        stop.addActionListener(e -> execute(this.player, "Stop"));
        JButton next = new JButton("Next");
        // next.addActionListener(e -> textField.setText(player.getState().onNext()));
        next.addActionListener(e -> execute(this.player, "Next"));
        JButton prev = new JButton("Prev");
        // prev.addActionListener(e ->
        // textField.setText(player.getState().onPrevious()));
        prev.addActionListener(e -> execute(this.player, "Prev"));
        frame.setVisible(true);
        frame.setSize(300, 100);
        buttons.add(play);
        buttons.add(stop);
        buttons.add(next);
        buttons.add(prev);
    }

    private static void execute(PlayerApplication app, String button) {
        // Here is how state mechanics work: the previous state
        // executes an action and returns a new state.
        // Each state has all 4 operations but reacts differently.
        switch (button) {
            case "Play":
                app.state = app.state.play(app.player);
                break;
            case "Stop":
                app.state = app.state.stop(app.player);
                break;
            case "Prev":
                if (app.state instanceof StateDefault) {
                    app.state = ((StateDefault) app.state).prev(app.player);
                }
                break;
            case "Next":
                if (app.state instanceof StateDefault) {
                    app.state = ((StateDefault) app.state).next(app.player);
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected button: " + button);
        }
         // Render the new state
         // TODO implement this in state and let render return current status as string
         //textField.setText(app.state.getStatus())
         //or
         //textField.setText(app.getState().getStatus())
        app.state.render(app.player, textField);
    }
}