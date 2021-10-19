package model.picture;

import model.ShapeType;
import java.awt.*;

public class Triangle implements Shape{
    private ShapeType shapeType = ShapeType.TRIANGLE;
    private Color color;
    private Point start;
    private Point end;
    private int[] xPoints;
    private int[] yPoints;

    private int left;
    private int right;
    private int top;
    private int bottom;

    public Triangle(Color color, Point start, Point end){
        this.color = color;
        this.start = start;
        this.end = end;
        this.xPoints = new int[]{end.getX(), (start.getX()+ end.getX())/2,    start.getX()};
        this.yPoints = new int[]{end.getY(), start.getY(),                    end.getY()};

        this.right = (Math.max(start.getX(), end.getX()));
        this.left = (Math.min(start.getX(), end.getX()));
        this.bottom = (Math.max(start.getY(), end.getY()));
        this.top = (Math.min(start.getY(), end.getY()));
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean equals(Shape shape2) {
        return shape2.getShapeType().equals(shapeType) &&
                shape2.getColor().equals(color) &&
                shape2.getStart().equals(start) &&
                shape2.getEnd().equals(end);
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Point getStart() {
        return start;
    }

    @Override
    public Point getEnd() {
        return end;
    }

    @Override
    public Boolean intersects(Point p1, Point p2) {
        int interLeft =     (Math.min(p1.getX(), p2.getX()));
        int interRight =    (Math.max(p1.getX(), p2.getX()));
        int interTop =      (Math.min(p1.getY(), p2.getY()));
        int interBottom =   (Math.max(p1.getY(), p2.getY()));

        return left < interRight && right > interLeft && top < interBottom && bottom > interTop;
    }
}
