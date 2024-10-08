package me.study.designpatterns.creational.abstract_factory.examples.example08_cross_platform_gui;

public class BeforeTest {

    public static void main(String[] args) {
        Client c = new Client(Platform.LINUX);
        c.draw();
    }
    static  enum Platform {
        LINUX, WINDOWS
    }
    static  interface Widget {
        void draw();
    }
    
    static class LinuxButton implements Widget {
        public void draw() {
            System.out.println("LinuxButton");
        }
    }
    
    static class LinuxMenu implements Widget {
        public void draw() {
            System.out.println("LinuxMenu");
        }
    }
    
    static class WindowsButton implements Widget {
        public void draw() {
            System.out.println("WindowsButton");
        }
    }
    
    static class WindowsMenu implements Widget {
        public void draw() {
            System.out.println("WindowsMenu");
        }
    }
    
    static class Client {
        Platform platform = Platform.LINUX;
    
        public Client(Platform platform) {
            this.platform = platform;
        }
    
        public void draw() {
            Widget w;
            if (platform == Platform.LINUX) {
                w = new LinuxButton();
            } else {
                w = new WindowsButton();
    
            }
            w.draw();
            displayWindowOne();
            displayWindowTwo();
        }
    
        public void displayWindowOne() {
            Widget[] w;
            if (platform == Platform.LINUX) {
                w = new Widget[] { new LinuxButton(), new LinuxMenu() };
            } else
    
            {
                w = new Widget[] { new WindowsButton(), new WindowsMenu() };
            }
            w[0].draw();
            w[1].draw();
        }
    
        public void displayWindowTwo() {
            Widget[] w;
            if (platform == Platform.LINUX) {
                w = new Widget[] { new LinuxMenu(), new LinuxButton() };
            } else
    
            {
                w = new Widget[] { new WindowsMenu(), new WindowsButton() };
            }
            w[0].draw();
            w[1].draw();
        }
    }
}
