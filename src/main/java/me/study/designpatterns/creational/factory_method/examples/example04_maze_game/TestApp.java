package me.study.designpatterns.creational.factory_method.examples.example04_maze_game;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        MazeGame ordinaryGame = new OrdinaryMazeGame();
        MazeGame magicGame = new MagicMazeGame();
        run(ordinaryGame);
        run(magicGame);
    }

    static void run(MazeGame mazeGame) {
        System.out.println("Loading resources...");
        System.out.println("Starting the game...");

        mazeGame.play();
    }
}

abstract class Room {
    abstract void connect(Room room);

    abstract void render();
}

class MagicRoom extends Room {
    String title;

    public MagicRoom(String title) {
        this.title = title;
    }

    void connect(Room room) {
    }

    @Override
    void render() {
        System.out.println("Magic Room: " + title);
    }
}

class OrdinaryRoom extends Room {
    String title;

    public OrdinaryRoom(String title) {
        this.title = title;
    }

    void connect(Room room) {
    }

    @Override
    void render() {
        System.out.println("Ordinary Room: " + title);
    }
}

abstract class MazeGame {
    private final List<Room> rooms = new ArrayList<>();

    MazeGame() {
        Room room1 = makeRoom("Infinite Room");
        Room room2 = makeRoom("Red Room");
        room1.connect(room2);
        rooms.add(room1);
        rooms.add(room2);
    }

    abstract protected Room makeRoom(String title);

    public void play() {
        for (Room room : rooms) {
            room.render();
        }
    }
}

class MagicMazeGame extends MazeGame {
    @Override
    protected MagicRoom makeRoom(String title) {
        return new MagicRoom(title);
    }
}

class OrdinaryMazeGame extends MazeGame {
    @Override
    protected OrdinaryRoom makeRoom(String title) {
        return new OrdinaryRoom(title);
    }
}