package me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Interpreter"
 */
public class Subtract extends Symbol {

    /**
     * This is the subtraction symbol
     *
     * @return true
     */
    @Override
    public boolean isMinus() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return " - ";
    }
}