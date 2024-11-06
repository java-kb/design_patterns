package me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes;

import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.visitor.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
