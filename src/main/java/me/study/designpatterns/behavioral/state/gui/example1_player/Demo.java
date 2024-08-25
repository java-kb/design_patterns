package me.study.designpatterns.behavioral.state.gui.example1_player;

import me.study.designpatterns.behavioral.state.gui.example1_player.ui.Player;
import me.study.designpatterns.behavioral.state.gui.example1_player.ui.UI;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
