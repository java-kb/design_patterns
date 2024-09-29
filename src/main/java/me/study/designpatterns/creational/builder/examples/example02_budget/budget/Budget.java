package me.study.designpatterns.creational.builder.examples.example02_budget.budget;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import me.study.designpatterns.creational.builder.examples.example02_budget.budget.builder.Builder;
import me.study.designpatterns.creational.builder.examples.example02_budget.budget.builder.HTMLBuilder;
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
public final class Budget {

    /**
     * The XML source file with all the items in a tree structure
     */
    private final String file = "BudgetBook.xml";

    /**
     * Read the file and convert the content to the TreeMode. Then show the
     * structure
     */
    Budget() {
        var builder = new HTMLBuilder();
        build(builder);
        var html = builder.getProduct();
        System.out.println(html);
    }

    /**
     * Read file, parse it and call the builder
     *
     * @param builder for the structure
     */
    public void build(Builder builder) {
        
        try {
            URL path = Budget.class.getResource(file);
            var filePath = Paths.get(path.getPath());
            var content = Files.readString(filePath);

            var factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            var saxParser = factory.newSAXParser();

            saxParser.parse(new InputSource(new StringReader(content)), builder);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create the new BudgetBook
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        new Budget();
    }
}
