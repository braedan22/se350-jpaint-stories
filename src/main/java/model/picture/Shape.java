package model.picture;

import model.ShapeType;

import java.awt.*;

public interface Shape {
    void draw(Graphics graphics);

    boolean equals(Shape shape2);

    ShapeType getShapeType();

    Color getColor();

    Point getStart();

    Point getEnd();

    Boolean intersects(Point p1, Point p2);
}
