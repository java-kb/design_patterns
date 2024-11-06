package me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes;

import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.visitor.Visitor;

public class Circle extends Dot {
    private int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }
}
