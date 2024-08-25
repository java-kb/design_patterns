package me.study.designpatterns.behavioral.state.gui.example2_music_player;

import java.util.ArrayList;
import java.util.List;

// A music player holds a playlist and it can do basic operations over it.
class Player {
    private List<Track> playlist;
    private int currentTrack;
    private int volume;

    public Player() {
        this.playlist = new ArrayList<>();
        this.playlist.add(new Track("Track 1", 180));
        this.playlist.add(new Track("Track 2", 165));
        this.playlist.add(new Track("Track 3", 197));
        this.playlist.add(new Track("Track 4", 205));
        this.currentTrack = 0;
        this.volume = 25;
    }

    public void nextTrack() {
        this.currentTrack = (this.currentTrack + 1) % this.playlist.size();
    }

    public void prevTrack() {
        this.currentTrack = (this.playlist.size() + this.currentTrack - 1) % this.playlist.size();
    }

    public void play() {
        this.activeTrack().setCursor(10); // Playback imitation.
    }

    public void pause() {
        this.activeTrack().setCursor(34); // Paused at some moment.
    }

    public void rewind() {
        this.activeTrack().setCursor(0);
    }

    public Track track() {
        return this.playlist.get(this.currentTrack);
    }

    private Track activeTrack() {
        return this.playlist.get(this.currentTrack);
    }
}