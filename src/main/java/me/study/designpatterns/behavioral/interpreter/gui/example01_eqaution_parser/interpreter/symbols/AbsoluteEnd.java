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
public class AbsoluteEnd extends Symbol {

    /**
     * This is the end of the absolute expression
     *
     * @return true
     */
    @Override
    public boolean isAbsoluteEnd() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return " ] ";
    }
}
