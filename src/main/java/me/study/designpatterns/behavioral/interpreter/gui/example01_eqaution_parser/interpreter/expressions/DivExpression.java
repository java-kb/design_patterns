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
public class DivExpression extends NonTerminalExpression {

    /**
     * Constructor. Gets the two sub expressions
     *
     * @param left part of the division
     * @param right part of the division
     */
    public DivExpression(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Divide the two results
     *
     * @return the result
     */
    @Override
    public double calc() {
        return left.calc() / right.calc();
    }

    /**
     * Print the result
     *
     * @return text
     */
    @Override
    public String toString() {
        return "DivExpression ( " + left + " " + right + " )";
    }
}
