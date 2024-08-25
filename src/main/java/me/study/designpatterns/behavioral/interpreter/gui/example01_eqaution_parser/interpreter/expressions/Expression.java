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
public interface Expression {

    /**
     * This is just the one necessary method for any expression
     *
     * @return the result of the calculation
     */
    double calc();
}
