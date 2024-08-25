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
public class Equals extends Symbol {

    /**
     * Chech for assignment
     *
     * @return in this case, always true
     */
    @Override
    public boolean isEquals() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return text
     */
    @Override
    public String toString() {
        return " = ";
    }
}
