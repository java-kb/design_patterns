package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issues_sol2;

import me.study.designpatterns.utils.ConsoleUtil;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Observer"
 */
public class TestApp {

    /**
     * Testing the observer pattern with an apartment market, a student and an
     * employee
     *
     * @param args are ignoted
     */
    public static void main(String[] args) {

        /*
         * The solution you saw in the Observer_03 project works flawlessly. But if you
         * want to work
         * with it on a concurrent system, you run into problems. Imagine that a thread
         * is handing
         * over a new flat and all observers are informed about it. At the same time,
         * another thread is
         * trying to unsubscribe an observer. There are then two threads that want to
         * access the list of
         * observers at the same time. Open the sample project Observer_04. I have
         * reduced it to the
         * most necessary classes so that you have, for example, only workers and no
         * students. Start
         * the main method of the class ObserverSim. This version starts two threads
         * (ApartmentThread
         * and DeRegisterThread) shortly after each other, both of which can work with
         * the list of
         * observers. The described situation, that two threads want to access the
         * database at the
         * same time, is provoked here deliberately. A concurrentModificationException
         * is thrown
         * after a short time. You may have to start the main method several times.
         */
        new TestApp().testObserverThread();
    }

    private void testObserverThread() {
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
