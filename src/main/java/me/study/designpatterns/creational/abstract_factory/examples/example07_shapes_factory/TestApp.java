package me.study.designpatterns.creational.abstract_factory.examples.example07_shapes_factory;

import java.util.*;

public class TestApp {
    public static void main(String[] args) {
        Factory factory;
        factory = new SimpleShapeFactory();

        Shape[] shapes = new Shape[3];

        shapes[0] = factory.createCurvedInstance(); // shapes[0] = new Ellipse();
        shapes[1] = factory.createStraightInstance(); // shapes[1] = new Rectangle();
        shapes[2] = factory.createCurvedInstance(); // shapes[2] = new Ellipse();

        for (int i = 0; i < 3; i++) {
            shapes[i].draw();
        }

        factory = new RobustShapeFactory();

        shapes = new Shape[3];

        shapes[0] = factory.createCurvedInstance(); // shapes[0] = new Ellipse();
        shapes[1] = factory.createStraightInstance(); // shapes[1] = new Rectangle();
        shapes[2] = factory.createCurvedInstance(); // shapes[2] = new Ellipse();

        for (int i = 0; i < 3; i++) {
            shapes[i].draw();
        }
    }
}

abstract class Shape {
    protected int id;
    private static int total = 0;

    public Shape() {
        id = total++;
    }

    public abstract void draw();
}

class Circle extends Shape {
    public void draw() {
        System.out.println("circle " + id + ": draw");
    }
}

class Square extends Shape {
    public void draw() {
        System.out.println("square " + id + ": draw");
    }
}

class Ellipse extends Shape {
    public void draw() {
        System.out.println("ellipse " + id + ": draw");
    }
}

class Rectangle extends Shape {
    public void draw() {
        System.out.println("rectangle " + id + ": draw");
    }
}

interface Factory {
    Shape createCurvedInstance();

    Shape createStraightInstance();
}

class SimpleShapeFactory implements Factory {
    public Shape createCurvedInstance() {
        return new Circle();
    }

    public Shape createStraightInstance() {
        return new Square();
    }
}

class RobustShapeFactory implements Factory {
    public Shape createCurvedInstance() {
        return new Ellipse();
    }

    public Shape createStraightInstance() {
        return new Rectangle();
    }
}
