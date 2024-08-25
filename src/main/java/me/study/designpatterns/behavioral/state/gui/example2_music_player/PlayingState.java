package me.study.designpatterns.behavioral.state.gui.example2_music_player;
import javax.swing.*;
class PlayingState implements StateDefault {
    @Override
    public State play(Player player) {
        player.pause();
        // Playing -> Paused.
        return new PausedState();
    }

    @Override
    public State stop(Player player) {
        player.pause();
        player.rewind();
        // Playing -> Stopped.
        return new StoppedState();
    }

    @Override
    public void render(Player player, JTextField view) {
        view.setText(String.format("[Playing] %s - %d sec", player.track().title, player.track().duration));
    }
}