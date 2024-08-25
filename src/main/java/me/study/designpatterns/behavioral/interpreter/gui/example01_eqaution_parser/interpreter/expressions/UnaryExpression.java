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
public final class UnaryExpression implements Expression {

    /**
     * The current expression
     */
    private final Expression expression;

    /**
     * Constructor of the unary minus expression
     *
     * @param expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Evaluates the unary minus
     *
     * @return the result
     */
    @Override
    public double calc() {
        return -(expression.calc());
    }

    /**
     * Printout the expression
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Unary minus ( " + expression + " )";
    }
}
