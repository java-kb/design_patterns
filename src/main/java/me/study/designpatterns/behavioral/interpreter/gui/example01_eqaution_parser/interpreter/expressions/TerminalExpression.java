package me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions;

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
public final class TerminalExpression implements Expression {

    /**
     * The current value of the expression
     */
    private final double value;

    /**
     * Constructor. Gets the value
     *
     * @param value
     */
    public TerminalExpression(double value) {
        this.value = value;
    }

    /**
     * Just return the value as the result
     *
     * @return the value
     */
    @Override
    public double calc() {
        return value;
    }

    /**
     * Printout the expression
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Terminal ( " + Double.toString(value) + " )";
    }
}
