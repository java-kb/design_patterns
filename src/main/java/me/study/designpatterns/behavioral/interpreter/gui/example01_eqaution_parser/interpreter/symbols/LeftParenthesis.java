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
public class LeftParenthesis extends Symbol {

    /**
     * This is the opening parenthesis
     *
     * @return true
     */
    @Override
    public boolean isOpeningParenthesis() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return " ( ";
    }
}
