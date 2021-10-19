package model.picture;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Picture {
    private static List<Shape> shapes = new ArrayList<>();

    public static void add(Shape shape){
        shapes.add(shape);
    }

    public static void remove(Shape shape){
        shapes.remove(shape);
    }

    public static void draw(Graphics graphics){
        for(Shape shape:shapes) {
            shape.draw(graphics);
        }
    }

    public static List<Shape> select(Point start, Point end){
        List<Shape> output = new ArrayList<>();
        for(Shape shape:shapes) {
            if(shape.intersects(start, end)){
                output.add(shape);
            }
        }
        return output;
    }
}
