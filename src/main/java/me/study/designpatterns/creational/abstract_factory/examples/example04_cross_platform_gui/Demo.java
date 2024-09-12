package me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui;

import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.app.Application;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories.GUIFactory;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories.MacOSFactory;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories.WindowsFactory;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {

    /**
     * Application picks the factory type and creates it in run time (usually at
     * initialization stage), depending on the configuration or environment
     * variables.
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
            app = new Application(factory);
        } else {
            factory = new WindowsFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
