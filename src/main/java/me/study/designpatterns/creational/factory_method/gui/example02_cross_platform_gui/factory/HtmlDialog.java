package me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.factory;

import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.factory_method.gui.example02_cross_platform_gui.buttons.HtmlButton;

/**
 * HTML Dialog will produce HTML buttons.
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
