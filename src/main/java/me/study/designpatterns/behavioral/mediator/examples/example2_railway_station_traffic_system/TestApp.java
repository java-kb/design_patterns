package me.study.designpatterns.behavioral.mediator.examples.example2_railway_station_traffic_system;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        StationManager stationManager = new StationManager();

        PassengerTrain passengerTrain = new PassengerTrain(stationManager);
        FreightTrain freightTrain = new FreightTrain(stationManager);

        passengerTrain.arrive();
        freightTrain.arrive();
        passengerTrain.depart();
    }
}

interface Train {
    void arrive();

    void depart();

    void permitArrival();
}

interface Mediator {
    boolean canArrive(Train train);

    void notifyAboutDeparture();
}

class PassengerTrain implements Train {
    private Mediator mediator;

    public PassengerTrain(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void arrive() {
        if (!mediator.canArrive(this)) {
            System.out.println("PassengerTrain: Arrival blocked, waiting");
            return;
        }
        System.out.println("PassengerTrain: Arrived");
    }

    @Override
    public void depart() {
        System.out.println("PassengerTrain: Leaving");
        mediator.notifyAboutDeparture();
    }

    @Override
    public void permitArrival() {
        System.out.println("PassengerTrain: Arrival permitted, arriving");
        arrive();
    }
}

class FreightTrain implements Train {
    private Mediator mediator;

    public FreightTrain(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void arrive() {
        if (!mediator.canArrive(this)) {
            System.out.println("FreightTrain: Arrival blocked, waiting");
            return;
        }
        System.out.println("FreightTrain: Arrived");
    }

    @Override
    public void depart() {
        System.out.println("FreightTrain: Leaving");
        mediator.notifyAboutDeparture();
    }

    @Override
    public void permitArrival() {
        System.out.println("FreightTrain: Arrival permitted");
        arrive();
    }
}

class StationManager implements Mediator {
    private boolean isPlatformFree;
    private List<Train> trainQueue;

    public StationManager() {
        isPlatformFree = true;
        trainQueue = new ArrayList<>();
    }

    @Override
    public boolean canArrive(Train train) {
        if (isPlatformFree) {
            isPlatformFree = false;
            return true;
        }
        trainQueue.add(train);
        return false;
    }

    @Override
    public void notifyAboutDeparture() {
        if (!isPlatformFree) {
            isPlatformFree = true;
        }
        if (!trainQueue.isEmpty()) {
            Train firstTrainInQueue = trainQueue.remove(0);
            firstTrainInQueue.permitArrival();
        }
    }
}
