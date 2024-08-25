package me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.AbsoluteEnd;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.AbsoluteStart;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Add;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Divide;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Equals;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.LeftParenthesis;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Literal;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Multiply;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Numeral;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.RightParenthesis;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Subtract;
import me.study.designpatterns.behavioral.interpreter.gui.example01_eqaution_parser.interpreter.symbols.Symbol;

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
public class Scanner {

    /**
     * List of scanned symbols
     */
    private final List<Symbol> symbols = new ArrayList<>();
    /**
     * A hashmap of the valid operator symbols
     */
    private final Map<Character, Symbol> operators = new HashMap<>();

    /**
     * Constructor. Initializes the operators hashmap
     */
    private Scanner() {
        operators.put('+', new Add());
        operators.put('-', new Subtract());
        operators.put('*', new Multiply());
        operators.put('/', new Divide());
        operators.put('[', new AbsoluteStart());
        operators.put(']', new AbsoluteEnd());
        operators.put('(', new LeftParenthesis());
        operators.put(')', new RightParenthesis());
        operators.put('=', new Equals());
    }

    /**
     * Constructor. Splits the input into a list of its symbols
     *
     * @param input the entered text
     *
     * @throws IllegalArgumentException
     */
    public Scanner(String input) throws IllegalArgumentException {
        this();
        input = input.replaceAll(" ", "");
        var length = input.length();
        var firstSemicolon = input.indexOf(';');
        if (firstSemicolon != length - 1)
            throw new IllegalArgumentException("You must end your input with a semicolon. ");
        var lastCharacter = input.charAt(length - 1);
        if (lastCharacter != ';')
            throw new IllegalArgumentException("The last character must be a \";\" sign.");
        else
            for (var i = 0; i < input.length(); i++) {
                Character character = input.charAt(i);
                if (!character.equals(';'))
                    if (operators.containsKey(character))
                        symbols.add(operators.get(character));
                    else if (Character.isLetter(character))
                        symbols.add(new Literal(character));
                    else {
                        var numberBuilder = new StringBuilder();
                        while (Character.isDigit(character)) {
                            numberBuilder.append(character);
                            character = input.charAt(++i);
                        }
                        if (character == '.' || character == ',') {
                            character = '.';
                            numberBuilder.append(character);
                            i++;
                            character = input.charAt(i);
                            while (Character.isDigit(character)) {
                                numberBuilder.append(character);
                                character = input.charAt(++i);
                            }
                        }
                        symbols.add(new Numeral(numberBuilder.toString()));
                        i--;
                    }
            }
    }

    /**
     * Get all the scanned symbols
     *
     * @return the list of symbols (unmodifiable)
     */
    public List<Symbol> getSymbols() {
        return Collections.unmodifiableList(symbols);
    }

    /**
     * Printout the symbols
     *
     * @return the list converted into a string
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var symbol : symbols)
            builder.append(symbol.toString());
        return builder.toString();
    }
}
