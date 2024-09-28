package me.study.designpatterns.creational.prototype.examples.example05_stooge;

import java.util.ArrayList;

public class AfterTestApp {
    public static void main(String[] args) {
        ArrayList<Stooge> roles = new ArrayList<>();
        int choice;

        while (true) {
            System.out.print("Larry(1) Moe(2) Curly(3) Go(0): ");
            choice = Integer.parseInt(System.console().readLine());
            if (choice == 0)
                break;
            roles.add(Factory.make_stooge(choice));
        }

        for (Stooge stooge : roles)
            stooge.slap_stick();
        for (Stooge stooge : roles)
            stooge = null;
    }
}

interface Stooge {
    default public Stooge clone() {
        return null;
    }

    default public void slap_stick() {
    }
}

class Factory {
    private static Stooge[] prototypes = { null, new Larry(), new Moe(), new Curly() };

    public static Stooge make_stooge(int choice) {
        return prototypes[choice].clone();
    }
}

class Larry implements Stooge {
    @Override
    public Stooge clone() {
        return new Larry();
    }

    @Override
    public void slap_stick() {
        System.out.println("Larry: poke eyes");
    }
}

class Moe implements Stooge {
    @Override
    public Stooge clone() {
        return new Moe();
    }

    @Override
    public void slap_stick() {
        System.out.println("Moe: slap head");
    }
}

class Curly implements Stooge {
    @Override
    public Stooge clone() {
        return new Curly();
    }

    @Override
    public void slap_stick() {
        System.out.println("Curly: suffer abuse");
    }
}
