package model.picture;

import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

public interface Shape {
    void draw(Graphics graphics);

    boolean equals(Shape shape2);

    Shape clone();

    ShapeType getShapeType();

    Color getPrimary();

    Color getSecondary();

    ShapeShadingType getTreatment();

    Point getStart();

    Point getEnd();

    void setStart(Point point);

    void setEnd(Point point);

    Boolean intersects(Point p1, Point p2);
}
