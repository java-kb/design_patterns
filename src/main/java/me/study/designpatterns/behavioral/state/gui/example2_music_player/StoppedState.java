package me.study.designpatterns.behavioral.state.gui.example2_music_player;
import javax.swing.*;
class StoppedState implements State {
    @Override
    public State play(Player player) {
        player.play();
        // Stopped -> Playing.
        return new PlayingState();
    }

    @Override
    public State stop(Player player) {
        // Change no state.
        return this;
    }

    @Override
    public void render(Player player, JTextField view) {
        view.setText("[Stopped] Press 'Play'");
    }
}