package me.study.designpatterns.creational.builder.gui.example01_budget.budget;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import me.study.designpatterns.creational.builder.gui.example01_budget.budget.builder.Builder;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.builder.TreeModelBuilder;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.tree.MyTreeCellEditor;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.tree.MyTreeCellRenderer;

import java.io.IOException;
import java.io.File;
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
        var builder = new TreeModelBuilder();
        build(builder);
        var treeModel = (TreeModel) builder.getProduct();
        var frmMain = new JFrame("Builder Pattern Demo");
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var trvBudgetBook = new JTree(treeModel);
        trvBudgetBook.setCellRenderer(new MyTreeCellRenderer());
        trvBudgetBook.setEditable(true);
        trvBudgetBook.setCellEditor(new MyTreeCellEditor());
        var scrTree = new JScrollPane(trvBudgetBook);
        frmMain.add(scrTree);
        frmMain.setSize(500, 500);
        frmMain.setVisible(true);
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
