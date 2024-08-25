package me.study.designpatterns.behavioral.mediator.examples.example3_railway_station_traffic_system;

import java.util.*;

public class TestApp {
    public static void main(String[] args) {
        Train train1 = new PassengerTrain("Train 1");
        Train train2 = new FreightTrain("Train 2");

        TrainStation station = new TrainStation();

        station.accept(train1);
        station.accept(train2);

        station.depart("Train 1");
        station.depart("Train 2");
        station.depart("Train 3");
    }
}
interface Train {
    String getName();
    void arrive(Mediator mediator);
    void depart(Mediator mediator);
}

interface Mediator {
    boolean notifyAboutArrival(String trainName);
    void notifyAboutDeparture(String trainName);
}

class TrainStation implements Mediator {
    private Map<String, Train> trains = new HashMap<>();
    private Deque<String> trainQueue = new ArrayDeque<>();
    private String trainOnPlatform = null;

    @Override
    public boolean notifyAboutArrival(String trainName) {
        if (trainOnPlatform != null) {
            trainQueue.addLast(trainName);
            return false;
        } else {
            trainOnPlatform = trainName;
            return true;
        }
    }

    @Override
    public void notifyAboutDeparture(String trainName) {
        if (trainName.equals(trainOnPlatform)) {
            trainOnPlatform = null;

            if (!trainQueue.isEmpty()) {
                String nextTrainName = trainQueue.removeFirst();
                Train nextTrain = trains.remove(nextTrainName);
                nextTrain.arrive(this);
                trains.put(nextTrainName, nextTrain);

                trainOnPlatform = nextTrainName;
            }
        }
    }

    public void accept(Train train) {
        if (trains.containsKey(train.getName())) {
            System.out.println(train.getName() + " has already arrived");
            return;
        }

        train.arrive(this);
        trains.put(train.getName(), train);
    }

    public void depart(String name) {
        Train train = trains.remove(name);
        if (train != null) {
            train.depart(this);
        } else {
            System.out.println("'" + name + "' is not on the station!");
        }
    }
}

class FreightTrain implements Train {
    private String name;

    public FreightTrain(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void arrive(Mediator mediator) {
        if (!mediator.notifyAboutArrival(name)) {
            System.out.println("Freight train " + name + ": Arrival blocked, waiting");
            return;
        }

        System.out.println("Freight train " + name + ": Arrived");
    }

    @Override
    public void depart(Mediator mediator) {
        System.out.println("Freight train " + name + ": Leaving");
        mediator.notifyAboutDeparture(name);
    }
}

class PassengerTrain implements Train {
    private String name;

    public PassengerTrain(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void arrive(Mediator mediator) {
        if (!mediator.notifyAboutArrival(name)) {
            System.out.println("Passenger train " + name + ": Arrival blocked, waiting");
            return;
        }

        System.out.println("Passenger train " + name + ": Arrived");
    }

    @Override
    public void depart(Mediator mediator) {
        System.out.println("Passenger train " + name + ": Leaving");
        mediator.notifyAboutDeparture(name);
    }
}




