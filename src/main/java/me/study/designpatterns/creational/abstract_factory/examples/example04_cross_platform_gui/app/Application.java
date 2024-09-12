package me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.app;

import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.Checkbox;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories.GUIFactory;

/**
 * Factory users don't care which concrete factory they use since they work with
 * factories and products through abstract interfaces.
 */
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
