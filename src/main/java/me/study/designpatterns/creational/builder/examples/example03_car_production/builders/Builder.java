package me.study.designpatterns.creational.builder.examples.example03_car_production.builders;

import me.study.designpatterns.creational.builder.examples.example03_car_production.cars.Type;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.Engine;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.GPSNavigator;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.Transmission;
import me.study.designpatterns.creational.builder.examples.example03_car_production.components.TripComputer;

/**
 * Builder interface defines all possible ways to configure a product.
 */
public interface Builder {
    void setType(Type type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
