package me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.factories;

import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.buttons.Button;
import me.study.designpatterns.creational.abstract_factory.examples.example04_cross_platform_gui.checkboxes.Checkbox;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
