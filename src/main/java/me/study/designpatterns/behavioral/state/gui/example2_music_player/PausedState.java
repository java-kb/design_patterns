package me.study.designpatterns.behavioral.state.gui.example2_music_player;
import javax.swing.*;

class PausedState implements StateDefault {
    @Override
    public State play(Player player) {
        player.pause();
        // Paused -> Playing.
        return new PlayingState();
    }

    @Override
    public State stop(Player player) {
        player.pause();
        player.rewind();
        // Paused -> Stopped.
        return new StoppedState();
    }

    @Override
    public void render(Player player, JTextField view) {
        view.setText(String.format("[Paused] %s - %d sec", player.track().title, player.track().duration));
    }
}