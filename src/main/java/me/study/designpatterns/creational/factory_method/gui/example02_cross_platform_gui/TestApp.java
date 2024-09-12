package me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui;

import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.factory.Dialog;
import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.factory.HtmlDialog;
import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.factory.WindowsDialog;

/**
 * Demo class. Everything comes together here.
 */
public class TestApp {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * The concrete factory is usually chosen depending on configuration or
     * environment options.
     */
    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * All of the client code should work with factories and products through
     * abstract interfaces. This way it does not care which factory it works
     * with and what kind of product it returns.
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
