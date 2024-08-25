package me.study.designpatterns.structural.composite.gui.example03_graphical_shapes;

import  me.study.designpatterns.structural.composite.gui.example03_graphical_shapes.editor.ImageEditor;
import  me.study.designpatterns.structural.composite.gui.example03_graphical_shapes.shapes.Circle;
import  me.study.designpatterns.structural.composite.gui.example03_graphical_shapes.shapes.CompoundShape;
import  me.study.designpatterns.structural.composite.gui.example03_graphical_shapes.shapes.Dot;
import  me.study.designpatterns.structural.composite.gui.example03_graphical_shapes.shapes.Rectangle;

import java.awt.*;

public class TestApp {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                    new Circle(110, 110, 50, Color.RED),
                    new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}