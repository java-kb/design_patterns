package me.study.designpatterns.creational.builder.examples.example03_car_production.director;

import me.study.designpatterns.creational.builder.examples.example03_car_production.builders.Builder;
import me.study.designpatterns.creational.builder.examples.example03_car_production.cars.Type;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.Engine;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.GPSNavigator;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.Transmission;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.TripComputer;

/**
 * Director defines the order of building steps. It works with a builder object
 * through common Builder interface. Therefore it may not know what product is
 * being built.
 */
public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setType(Type.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setType(Type.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setType(Type.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
}
