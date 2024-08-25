package me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter;


import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.DivExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.Expression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.MinusExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.MultExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.PlusExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.TerminalExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.expressions.UnaryExpression;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.EndSymbol;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Symbol;
import java.util.Iterator;
import java.util.List;

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
public final class Parser {

    /**
     * An iterator on the list of symbols
     */
    private final Iterator<Symbol> iterator;
    /**
     * The root of the abstract syntax tree
     */
    private final Expression root;
    /**
     * The current symbol in processing
     */
    private Symbol currentSymbol;

    /**
     * Constructor. Assigns the iterator and starts parsing the expression
     *
     * @param symbols the list of symbols out of the scanner
     */
    public Parser(List<Symbol> symbols) {
        iterator = symbols.iterator();
        nextSymbol();
        root = parseExpression();
    }

    /**
     * Sets the current symbol to the next in the list. If list end is reached,
     * an end symbol is set
     */
    private void nextSymbol() {
        currentSymbol = iterator.hasNext() ? iterator.next() : new EndSymbol();
    }

    /**
     * Starts parsing an expression
     *
     * @return the parsed expression
     */
    private Expression parseExpression() {
        Expression expression = this.parseAddSub();
        return expression;
    }

    /**
     * Takes care of addition and substraction. But according to the operator
     * precedence, first takes a look at multiplication and division
     *
     * @return the expression
     */
    private Expression parseAddSub() {
        var expression = this.parseMultDiv();
        while (currentSymbol.isPlus() || currentSymbol.isMinus()) {
            var add = currentSymbol.isPlus();
            nextSymbol();
            var right = this.parseMultDiv();
            expression = add
                    ? new PlusExpression(expression, right)
                    : new MinusExpression(expression, right);
        }
        return expression;
    }

    /**
     * Takes care of multiplication and division. But according to the operator
     * precedence, first takes a look at unary leading signs
     *
     * @return the expression
     */
    private Expression parseMultDiv() {
        var expression = this.parseLeadingSign();
        while (currentSymbol.isMultiply() || currentSymbol.isDivide()) {
            var mult = currentSymbol.isMultiply();
            nextSymbol();
            var right = this.parseLeadingSign();
            expression = mult
                    ? new MultExpression(expression, right)
                    : new DivExpression(expression, right);
        }
        return expression;
    }

    /**
     * Parses a unary leading sign expression
     *
     * @return the expression
     */
    private Expression parseLeadingSign() {
        Expression expression;
        var minus = currentSymbol.isMinus();
        if (currentSymbol.isMinus() || currentSymbol.isPlus())
            nextSymbol();
        expression = this.parseNumber();
        if (minus)
            expression = new UnaryExpression(expression);
        return expression;
    }

    /**
     * Parses and converts a number
     *
     * @return the expression
     */
    private Expression parseNumber() {
        if (!currentSymbol.isNumeral())
            throw new IllegalStateException("Current symbol is not a number: " + currentSymbol);
        var value = Double.parseDouble(currentSymbol.toString());
        nextSymbol();
        return new TerminalExpression(value);
    }

    /**
     * The start of the expression
     *
     * @return the start
     */
    public Expression getRoot() {
        return root;
    }
}
