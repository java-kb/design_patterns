package me.study.designpatterns.creational.builder.examples.example02_budget.budget.builder;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Builder"
 */
public abstract class Builder extends DefaultHandler {

    /**
     * Return the result of the build process
     *
     * @return the built object
     */
    public abstract Object getProduct();

    /**
     * Handle the start of a new element
     *
     * @param uri
     * @param localName
     * @param name of the XML element
     * @param attributes attributes of the XML element
     */
    @Override
    public abstract void startElement(String uri, String localName, String name,
            Attributes attributes);

    /**
     * Handle the ending of an element
     *
     * @param uri
     * @param localName
     * @param name name of the XML element
     */
    @Override
    public abstract void endElement(String uri, String localName, String name);

    /**
     * Issue a warning
     *
     * @param exception to be shown
     */
    @Override
    public void warning(SAXParseException exception) {
        System.err.println("-> warning: " + exception.getMessage());
    }

    /**
     * Issue a fatal error
     *
     * @param exception to be shown
     */
    @Override
    public void fatalError(SAXParseException exception) {
        System.err.println("-> FATAL ERROR: " + exception.getMessage());
    }

    /**
     * Issue an error
     *
     * @param exception to be shown
     */
    @Override
    public void error(SAXParseException exception) {
        System.err.println("-> error: " + exception.getMessage());
    }
}
