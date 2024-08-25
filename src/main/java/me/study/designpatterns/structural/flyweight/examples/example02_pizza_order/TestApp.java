package me.study.designpatterns.structural.flyweight.examples.example02_pizza_order;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        ConsoleUtil.Title("Before");
        new Before().run();
        ConsoleUtil.Title("After");
        new After().run();
    }
}

/*
 * You program a software that takes
 * orders for a pizzeria
 */
class Before {
    /*
     * Your frst approach might be to defne a Pizza Order class that stores the name
     * of the pizza
     * and the table to which it should be delivered
     */
    static class PizzaOrder {

        /**
         * The ordere pizza
         */
        public final String name;

        /**
         * Number of the table
         */
        public final int table;

        /**
         * Constructor. Gets the properties
         *
         * @param name  of pizza
         * @param table number of table
         */
        public PizzaOrder(String name, int table) {
            this.table = table;
            this.name = name;
            System.out.println("I'll make a " + name + " for table " + table);
        }
    }

    /*
     * The Client creates new order objects and stores them in a list. When each
     * table has
     * placed an order, the pizzas are served
     */
    /**
     * A list of all current orders
     */
    private static final List<PizzaOrder> orders = new LinkedList<>();

    /**
     * Take a new order
     *
     * @param table the number of the table
     * @param pizza the ordered pizza
     */
    private static void takeOrder(int table, String pizza) {
        orders.add(new PizzaOrder(pizza, table));
    }

    /**
     * Create some orders and print out everything
     *
     * @param args are ignored
     */
    public void run() {
        takeOrder(1, "Pizza Hawaii");
        takeOrder(2, "Pizza Funghi");
        takeOrder(3, "Pizza Carbonara");

        takeOrder(4, "Pizza Calzone");
        takeOrder(4, "Pizza Carbonara");

        takeOrder(5, "Pizza Vesuv");
        takeOrder(12, "Pizza Ätna");
        takeOrder(25, "Pizza Diavolo");
        takeOrder(32, "Pizza Carbonara");

        takeOrder(47, "Pizza Hawaii");
        takeOrder(47, "Pizza Calzone");
        takeOrder(47, "Pizza Diavolo");

        takeOrder(51, "Pizza Pazza");
        takeOrder(56, "Pizza Salami");

        takeOrder(61, "Pizza Diavolo");
        takeOrder(61, "Pizza Ätna");

        takeOrder(66, "Pizza Calzone");
        takeOrder(66, "Pizza Pazza");
        takeOrder(66, "Pizza Carbonara");

        takeOrder(70, "Pizza Hawaii");

        takeOrder(71, "Pizza Diavolo");
        takeOrder(71, "Pizza Funghi");

        takeOrder(75, "Pizza Carbonara");
        takeOrder(75, "Pizza Ätna");

        takeOrder(79, "Pizza Funghi");

        orders.forEach(pizza -> {
            System.out.println("Now serving " + pizza.name + " to table " + pizza.table);
        });
    }

}

/*
 * If you analyze the code, you will see that each pizza is ordered multiple
 * times. Of
 * course, in individual cases, it is also possible that two or more orders are
 * placed at one
 * table. Which attribute will you share – the pizza or the table number? It
 * takes a lot of effort
 * to make a pizza. So, since your goal is to save resources in your ordering
 * software, you
 * will share the pizza – but note: only in the software, of course, each guest
 * will end up with
 * the whole pizza of their choice. The number of the table the order comes from
 * will not be
 * shared – the client will be blamed for that right away. So you have two
 * different states: the
 * state that is shared; and the state that the client is held responsible for.
 * You call the shared
 * state intrinsic; the other extrinsic. Objects that defne the intrinsic state
 * are the fy-
 * weights. They are independent of the context in which they are used.
 * The PizzaOrder class has two attributes: the description of the pizza and the
 * number of the
 * table to which it should be delivered. You decompose the PizzaOrder class
 * into an intrinsic
 * state and an extrinsic state, where the description of the pizza itself
 * should be the intrinsic
 * state. So, you pull the other attribute out of the class and move it into the
 * context. What remains in the pizza class is only what is necessary to
 * describe the pizza. This is not the
 * baked pizza itself, but the note that it was ordered
 */
class After {
    static interface MenuEntry {

        /**
         * Deliver all orders to a table
         *
         * @param table the number of the table
         */
        void serve(int table);
    }

    /*
     * To allow for more dishes (salad, pasta, …) later on, I introduced the
     * interface
     * MenuEntry right away in this context, which is implemented by the class Pizza
     */
    static class Pizza implements MenuEntry {

        /**
         * The name of the pizza
         */
        public final String name;

        /**
         * Constructor, just gets the name
         *
         * @param name of pizza
         */
        public Pizza(String name) {
            this.name = name;
        }

        /**
         * Serve: Bring everything to the table
         *
         * @param table number of the table
         */
        @Override
        public void serve(int table) {
            System.out.println("" + name + " is serverd to table " + table + ".");
        }
    }

    static class MenuFactory {

        /**
         * Hold all the menus
         */
        private final Map<String, MenuEntry> menus = new HashMap<>();

        /**
         * Checks, if a menu already has been ordered once. Adds it, if not
         *
         * @param name the new menu
         *
         * @return the list of menus
         */
        public MenuEntry[] getMenu(String... name) {
            var numberOrders = name.length;
            var result = new MenuEntry[numberOrders];
            for (var i = 0; i < numberOrders; i++) {
                var menu = menus.get(name[i]);
                if (menu == null) {
                    menu = new Pizza(name[i]);
                    menus.put(name[i], menu);
                }
                result[i] = menu;
            }
            return result;
        }
    }
/*
 * The client manages the different pizza objects and the table numbers. To enable a 
unique assignment, the orders are stored in a map; the key of this map is the table number, 
the value is the ordered menus. In order to represent the situation that a table can place 
several orders, the menus are stored as arrays.
 */
    /**
     * Orders
     */
    private static final Map<Integer, MenuEntry[]> ORDERS = new HashMap<>();
    /**
     * The "kitchen"
     */
    private static final MenuFactory MENU_FACTORY = new MenuFactory();

    /**
     * Take a new order
     *
     * @param table the number of the table
     * @param menu  the menu
     */
    public static void takeOrder(int table, String... menu) {
        var order = MENU_FACTORY.getMenu(menu);
        ORDERS.put(table, order);
    }

    /**
     * Create some orders and list them
     *
     * @param args are ignored
     */
    public void run() {
        takeOrder(1, "Pizza Hawaii","Pizza Funghi");
        takeOrder(2, "Pizza Funghi");
        takeOrder(3, "Pizza Carbonara");
        takeOrder(4, "Pizza Calzone", "Pizza Carbonara");
        takeOrder(5, "Pizza Vesuv");
        takeOrder(12, "Pizza Ätna");
        takeOrder(25, "Pizza Diavolo");
        takeOrder(32, "Pizza Carbonara");
        takeOrder(47, "Pizza Hawaii", "Pizza Calzone",
                "Pizza Diavolo");
        takeOrder(51, "Pizza Pazza");
        takeOrder(56, "Pizza Salami");
        takeOrder(61, "Pizza Ätna", "Pizza Diavolo");
        takeOrder(66, "Pizza Calzone", "Pizza Pazza",
                "Pizza Carbonara");
        takeOrder(70, "Pizza Hawaii");
        takeOrder(71, "Pizza Diavolo", "Pizza Funghi");
        takeOrder(75, "Pizza Carbonara", "Pizza Ätna");
        takeOrder(79, "Pizza Funghi");

        ORDERS.keySet().forEach(tempTable -> {
            var menus = ORDERS.get(tempTable);
            for (var tempMenu : menus)
                tempMenu.serve(tempTable);
        });
    }

}