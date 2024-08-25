package me.study.designpatterns.behavioral.templatemethod.examples.example1;

public class UppercaseConverter extends Input {
    @Override
    protected String convert(String input) {
        return input.toUpperCase();
    }

}
