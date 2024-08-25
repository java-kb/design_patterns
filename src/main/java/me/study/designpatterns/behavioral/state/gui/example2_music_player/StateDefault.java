package me.study.designpatterns.behavioral.state.gui.example2_music_player;
import javax.swing.*;
// Default "next" and "prev" implementations for the State interface.
interface StateDefault extends State {
    default State next(Player player) {
        player.nextTrack();
        // Change no state.
        return this;
    }

    default State prev(Player player) {
        player.prevTrack();
        // Change no state.
        return this;
    }
    //@Override
    public default void render(Player player, JTextField view) {
        view.setText(String.format("[Playing] %s - %d sec", player.track().title, player.track().duration));
    }
}