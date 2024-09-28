package me.study.designpatterns.creational.prototype.examples.example03_copying_graphical_shapes.caching.cache;


import java.util.HashMap;
import java.util.Map;

import me.study.designpatterns.creational.prototype.examples.example03_copying_graphical_shapes.example.shapes.Circle;
import me.study.designpatterns.creational.prototype.examples.example03_copying_graphical_shapes.example.shapes.Rectangle;
import me.study.designpatterns.creational.prototype.examples.example03_copying_graphical_shapes.example.shapes.Shape;

public class BundledShapeCache {
    private Map<String, Shape> cache = new HashMap<>();

    public BundledShapeCache() {
        Circle circle = new Circle();
        circle.x = 5;
        circle.y = 7;
        circle.radius = 45;
        circle.color = "Green";

        Rectangle rectangle = new Rectangle();
        rectangle.x = 6;
        rectangle.y = 9;
        rectangle.width = 8;
        rectangle.height = 10;
        rectangle.color = "Blue";

        cache.put("Big green circle", circle);
        cache.put("Medium blue rectangle", rectangle);
    }

    public Shape put(String key, Shape shape) {
        cache.put(key, shape);
        return shape;
    }

    public Shape get(String key) {
        return cache.get(key).clone();
    }
}
