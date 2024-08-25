package me.study.designpatterns.behavioral.templatemethod.examples.example1;

import java.util.Scanner;

public abstract class Input {
    public final String run() {
        var input = textEnter();
        var converted = convert(input);
        print(converted);
        return converted;
    }

    private final String textEnter() {
        Scanner scanner = new Scanner(System.in); // 2
        System.out.println("Please enter the text:"); // 3
        return scanner.next();
    }

    protected abstract String convert(String input);

    private final void print(String text) {
        System.out.println(text);
    }
}