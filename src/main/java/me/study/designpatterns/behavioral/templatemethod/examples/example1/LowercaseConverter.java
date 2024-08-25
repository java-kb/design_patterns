package me.study.designpatterns.behavioral.templatemethod.examples.example1;

public class LowercaseConverter extends Input {
    @Override
    protected String convert(String input) {
        return input.toLowerCase();
    }

}
