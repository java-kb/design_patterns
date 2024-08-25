package me.study.designpatterns.structural.flyweight.examples.example03_game_dressing;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestApp {

    public static final String TerroristDressType = "tDress";
    public static final String CounterTerrroristDressType = "ctDress";

    public static void main(String[] args) {
        Game game = new Game();

        // Add Terrorist
        game.addTerrorist(TerroristDressType);
        game.addTerrorist(TerroristDressType);
        game.addTerrorist(TerroristDressType);
        game.addTerrorist(TerroristDressType);

        // Add CounterTerrorist
        game.addCounterTerrorist(CounterTerrroristDressType);
        game.addCounterTerrorist(CounterTerrroristDressType);
        game.addCounterTerrorist(CounterTerrroristDressType);

        DressFactory dressFactoryInstance = DressFactory.instance;

        for (String dressType : dressFactoryInstance.getDressMap().keySet()) {
            Dress dress = dressFactoryInstance.getDressMap().get(dressType);
            System.out.printf("DressColorType: %s\nDressColor: %s\n", dressType, dress.getColor());
        }
    }
}

interface Dress {
    String getColor();
}

enum DressType {
    TerroristDressType,
    CounterTerrroristDressType
}

class TerroristDress implements Dress {
    String color;

    public TerroristDress() {
        this.color = "red";
    }

    public String getColor() {
        return color;
    }
}

class CounterTerroristDress implements Dress {
    String color;

    public CounterTerroristDress() {
        this.color = "green";
    }

    public String getColor() {
        return color;
    }
}

class Player {
    Dress dress;
    String playerType;
    int lat;
    int lon;

    public Player(String playerType, String dressType) {
        this.dress = DressFactory.instance.getDressByType(dressType);
        this.playerType = playerType;
    }

    public void newLocation(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
    }
}

class DressFactory {
    public static final String TerroristDressType = "tDress";
    public static final String CounterTerrroristDressType = "ctDress";
    private Map<String, Dress> dressMap;
    public static DressFactory instance = new DressFactory();

    public Map<String, Dress> getDressMap() {
        return dressMap;
    }

    private DressFactory() {
        this.dressMap = new HashMap<>();
    }

    /*
     * public static DressFactory getDressFactorySingleInstance() {
     * if (instance != null)
     * return instance;
     * return new DressFactory();
     * }
     */

    public Dress getDressByType(String dressType) {
        if (dressMap.containsKey(dressType)) {
            return dressMap.get(dressType);
        }

        if (dressType.equals(TerroristDressType)) {
            dressMap.put(dressType, new TerroristDress());
            return dressMap.get(dressType);
        }
        if (dressType.equals(CounterTerrroristDressType)) {
            dressMap.put(dressType, new CounterTerroristDress());
            return dressMap.get(dressType);
        }

        return null;
    }
}

class Game {
    List<Player> terrorists;
    List<Player> counterTerrorists;

    public Game() {
        this.terrorists = new ArrayList<Player>();
        this.counterTerrorists = new ArrayList<Player>();
    }

    public void addTerrorist(String dressType) {
        Player player = new Player("T", dressType);
        terrorists.add(player);
    }

    public void addCounterTerrorist(String dressType) {
        Player player = new Player("CT", dressType);
        counterTerrorists.add(player);
    }
}