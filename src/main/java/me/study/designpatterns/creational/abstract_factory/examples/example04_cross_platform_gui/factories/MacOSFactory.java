package me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories;

import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.MacOSButton;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.Checkbox;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.MacOSCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
