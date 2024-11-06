package me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.visitor;

import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes.Circle;
import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes.CompoundShape;
import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes.Dot;
import me.study.designpatterns.behavioral.visitor.examples.example02_export_shapes.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
