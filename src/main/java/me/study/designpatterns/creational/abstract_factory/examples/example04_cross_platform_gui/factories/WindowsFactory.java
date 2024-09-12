package me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories;

import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.WindowsButton;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.Checkbox;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.WindowsCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
