package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public class Radio {
    /**
     * At start, the radio is off
     */
    private int volume = 0;

    /**
     * Turning on always sets volume to 1 Note: This will also work, if the
     * radio already is playing
     */
    public void turnOn() {
        volume = 1;
        System.out.println(">Radio: I'm on now.");
    }

    /**
     * Turning off always sets volume to 0
     */
    public void turnOff() {
        volume = 0;
        System.out.println(">Radio: I'm off now.");
    }

    /**
     * Decrease only works, when there's something to decrease
     */
    public void decreaseVolume() {
        if (volume >= 1) {
            volume--;
            System.out
                    .println(">Radio: I'm now playing softer: " + volume);
        }
    }

    /**
     * Increase always works
     */
    public void increaseVolume() {
        volume++;
        System.out.println(">Radio: I'm now playing louder: " + volume);
    }
}
