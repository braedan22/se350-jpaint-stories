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
}
