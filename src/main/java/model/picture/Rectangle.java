package model.picture;

import model.ShapeType;
import java.awt.*;

public class Rectangle implements Shape{
    private ShapeType shapeType = ShapeType.RECTANGLE;
    private Color color;
    private Point start;
    private Point end;
    private Point topLeft;
    private int width;
    private int height;

    public Rectangle(Color color, Point start, Point end){
        this.color = color;
        this.start = start;
        this.end = end;
        int maxX = (Math.max(start.getX(), end.getX()));
        int minX = (Math.min(start.getX(), end.getX()));
        int maxY = (Math.max(start.getY(), end.getY()));
        int minY = (Math.min(start.getY(), end.getY()));
        this.topLeft = new Point(minX, minY);
        this.width = maxX - minX;
        this.height = maxY - minY;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(topLeft.getX(), topLeft.getY(), width, height);
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
}
