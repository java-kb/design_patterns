package me.study.designpatterns.behavioral.state.gui.example2_music_player;

// A music track.
class Track {
    public String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int duration;
    private int cursor;

    public Track(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.cursor = 0;
    }
}