package me.study.designpatterns.creational.abstract_factory.examples.example08_cross_platform_gui;

public class AfterTest {

    public static void main(String[] args) {
        Factory factory;
        String os = "LINUX";

        if (os.equals("LINUX")) {
            factory = new LinuxFactory();
        } else {
            factory = new WindowsFactory();
        }

        Client c = new Client(factory);
        c.draw();
    }
}

interface Widget {
    void draw();
}

class LinuxButton implements Widget {
    public void draw() {
        System.out.println("LinuxButton");
    }
}

class LinuxMenu implements Widget {
    public void draw() {
        System.out.println("LinuxMenu");
    }
}

class WindowsButton implements Widget {
    public void draw() {
        System.out.println("WindowsButton");
    }
}

class WindowsMenu implements Widget {
    public void draw() {
        System.out.println("WindowsMenu");
    }
}

interface Factory {
    Widget create_button();

    Widget create_menu();
}

class LinuxFactory implements Factory {
    public Widget create_button() {
        return new LinuxButton();
    }

    public Widget create_menu() {
        return new LinuxMenu();
    }
}

class WindowsFactory implements Factory {
    public Widget create_button() {
        return new WindowsButton();
    }

    public Widget create_menu() {
        return new WindowsMenu();
    }
}

class Client {
    private Factory factory;

    public Client(Factory f) {
        factory = f;
    }

    public void draw() {
        Widget w = factory.create_button();
        w.draw();
        display_window_one();
        display_window_two();
    }

    public void display_window_one() {
        Widget[] w = {
                factory.create_button(),
                factory.create_menu()
        };
        w[0].draw();
        w[1].draw();
    }

    public void display_window_two() {
        Widget[] w = {
                factory.create_menu(),
                factory.create_button()
        };
        w[0].draw();
        w[1].draw();
    }
}