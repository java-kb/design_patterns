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
public class Literal extends Symbol {

    /**
     * The value of the literal (a character)
     */
    final char value;

    /**
     * Constructor. Gets the value
     *
     * @param character the new literal
     */
    public Literal(Character character) {
        this.value = character;
    }

    /**
     * This is a literal
     *
     * @return true
     */
    @Override
    public boolean isLiteral() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return Character.toString(value);
    }
}
