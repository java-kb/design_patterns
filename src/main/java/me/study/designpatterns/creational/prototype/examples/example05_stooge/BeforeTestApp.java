package me.study.designpatterns.creational.prototype.examples.example05_stooge;

import java.util.ArrayList;
import java.util.Scanner;

public class BeforeTestApp {
    public static void main(String[] args) {
        ArrayList<Stooge> roles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Larry(1) Moe(2) Curly(3) Go(0): ");
            choice = scanner.nextInt();
            if (choice == 0)
                break;
            else if (choice == 1)
                roles.add(new Larry());
            else if (choice == 2)
                roles.add(new Moe());
            else
                roles.add(new Curly());
        }

        for (Stooge stooge : roles)
            stooge.slap_stick();

        for (Stooge stooge : roles)
            if (stooge instanceof Larry)
                ((Larry) stooge).slap_stick();
            else if (stooge instanceof Moe)
                ((Moe) stooge).slap_stick();
            else if (stooge instanceof Curly)
                ((Curly) stooge).slap_stick();
    }
    static abstract class Stooge {
        public abstract void slap_stick();
    }
    
     static class Larry extends Stooge {
        public void slap_stick() {
            System.out.println("Larry: poke eyes");
        }
    }
    
    static class Moe extends Stooge {
        public void slap_stick() {
            System.out.println("Moe: slap head");
        }
    }
    
    static class Curly extends Stooge {
        public void slap_stick() {
            System.out.println("Curly: suffer abuse");
        }
    }
}


