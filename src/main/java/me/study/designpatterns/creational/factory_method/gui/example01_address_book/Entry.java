package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Factory Method"
 */
public interface Entry {

    /**
     * Every entry has to deliver an editor panel
     *
     * @return the panel of a specific entry
     */
    EntryPanel getEntryPanel();

    /**
     * Also, every entry needs a description
     *
     * @return text
     */
    String getDescription();
}
