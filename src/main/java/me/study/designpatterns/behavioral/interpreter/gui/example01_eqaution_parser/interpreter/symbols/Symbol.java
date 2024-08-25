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
public abstract class Symbol {

    /**
     * Check, if it's a literal symbol
     *
     * @return false by default
     */
    public boolean isLiteral() {
        return false;
    }

    /**
     * Check, if it's numeral a symbol
     *
     * @return false by default
     */
    public boolean isNumeral() {
        return false;
    }

    /**
     * Check, if it's a plus symbol
     *
     * @return false by default
     */
    public boolean isPlus() {
        return false;
    }

    /**
     * Check, if it's a minus symbol
     *
     * @return false by default
     */
    public boolean isMinus() {
        return false;
    }

    /**
     * Check, if it's a division symbol
     *
     * @return false by default
     */
    public boolean isDivide() {
        return false;
    }

    /**
     * Check, if it's a multiplication symbol
     *
     * @return false by default
     */
    public boolean isMultiply() {
        return false;
    }

    /**
     * Check, if it's an equals symbol
     *
     * @return false by default
     */
    public boolean isEquals() {
        return false;
    }

    /**
     * Check, if it's a ( symbol
     *
     * @return false by default
     */
    public boolean isOpeningParenthesis() {
        return false;
    }

    /**
     * Check, if it's a ) symbol
     *
     * @return false by default
     */
    public boolean isClosingParenthesis() {
        return false;
    }

    /**
     * Check, if it's an absolute value start symbol
     *
     * @return false by default
     */
    public boolean isAbsoluteStart() {
        return false;
    }

    /**
     * Check, if it's an absolute value end symbol
     *
     * @return false by default
     */
    public boolean isAbsoluteEnd() {
        return false;
    }
}
