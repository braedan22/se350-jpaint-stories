package model.picture;

import model.ShapeColor;
import model.interfaces.UserChoices;
import java.awt.*;

public class Rectangle implements Shape{
    private Color color;
    private Point start;
    private int width;
    private int height;

    public Rectangle(Color color, Point start, Point end){
        this.color = color;
        int maxX = (Math.max(start.getX(), end.getX()));
        int minX = (Math.min(start.getX(), end.getX()));
        int maxY = (Math.max(start.getY(), end.getY()));
        int minY = (Math.min(start.getY(), end.getY()));
        this.start = new Point(minX, minY);
        this.width = maxX - minX;
        this.height = maxY - minY;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(start.getX(), start.getY(), width, height);
    }
}
