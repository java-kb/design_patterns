package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issue;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issue.Apartment;
import me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issue.ApartmentMarket;
import me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issue.Employee;
import me.study.designpatterns.utils.ConsoleUtil;

public class TestAppTest {
    @Test
    public void testMain() {
        assertThrows(ConcurrentModificationException.class, () -> {
            // Register observers at the market
            market.addObserver(porthos);
            market.addObserver(athos);
            market.addObserver(aramis);

            // Start apartment thread
            (new ApartmentThread()).start();

            // Wait for 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                //
            }

            // Deregister one observer
            (new DeRegisterThread()).start();
        });

    }

    /**
     * A provider which is observed
     */
    private ApartmentMarket market = new ApartmentMarket();
    /**
     * An observer
     */
    private Employee athos = new Employee("Athos");
    /**
     * An observer
     */
    private Employee porthos = new Employee("Porthos");
    /**
     * An observer
     */
    private Employee aramis = new Employee("Aramis");

    /**
     * Adds two new apartments to the market
     */
    private class ApartmentThread extends Thread {

        @Override
        public void run() {
            ConsoleUtil.Title("Start ApartmentThread");
            System.out.println("Small Palace");
            market.addApartment(new Apartment("Small Palace"));
            System.out.println("Finca on Mallorca");
            market.addApartment(new Apartment("Finca on Mallorca"));
        }
    }

    /**
     * Deregisters Athos from the apartment market
     */
    private class DeRegisterThread extends Thread {

        @Override
        public void run() {
            ConsoleUtil.Title("Start DeregisterThread");
            market.removeObserver(athos);
            System.out.println("Athos is deregistered");
        }
    }
}
