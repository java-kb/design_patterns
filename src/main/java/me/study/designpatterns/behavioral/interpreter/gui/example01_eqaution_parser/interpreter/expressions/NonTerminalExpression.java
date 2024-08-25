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
public abstract class NonTerminalExpression implements Expression {

    /**
     * The left part of an expression, also an expression
     */
    protected final Expression left;
    /**
     * The right part of an expression, also an expression
     */
    protected final Expression right;

    /**
     * Constructor. Gets the two expressions of the left and right side
     *
     * @param left part
     * @param right part
     */
    protected NonTerminalExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
