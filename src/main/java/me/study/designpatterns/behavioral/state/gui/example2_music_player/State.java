package me.study.designpatterns.behavioral.state.gui.example2_music_player;
import javax.swing.*;

interface State {
    State play(Player player);
    State stop(Player player);
    void render(Player player, JTextField view);
}