package model.picture;

import java.util.ArrayList;
import java.util.List;

public class Selected {
    private static List<Shape> selected = new ArrayList<>();

    public static void set(List<Shape> shapes){
        selected = shapes;
    }

    public static List<Shape> get() {
        return selected;
    }

    public static void clear() {
        selected = new ArrayList<>();
    }
}
