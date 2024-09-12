package me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.factory;

import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.buttons.WindowsButton;

/**
 * Windows Dialog will produce Windows buttons.
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
