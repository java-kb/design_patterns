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
public class AbsoluteStart extends Symbol {

    /**
     * This is the start of an absolute expression
     *
     * @return true
     */
    @Override
    public boolean isAbsoluteStart() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return " [ ";
    }
}
