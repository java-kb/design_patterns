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
public class Numeral extends Symbol {

    /**
     * The value of the numeral expression
     */
    final double value;

    /**
     * Constructor. Gets the value as a string
     *
     * @param number text of the numeral
     */
    public Numeral(String number) {
        this.value = Double.valueOf(number);
    }

    /**
     * Constrcutor. Gets the value as a number
     *
     * @param number the value
     */
    public Numeral(double number) {
        this.value = number;
    }

    /**
     * This is a number
     *
     * @return true
     */
    @Override
    public boolean isNumeral() {
        return true;
    }

    /**
     * Printout the symbol
     *
     * @return symbol
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }
}
