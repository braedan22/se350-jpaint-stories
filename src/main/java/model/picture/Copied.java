package model.picture;

import java.util.ArrayList;
import java.util.List;

public class Copied {
    private static List<Shape> copied = new ArrayList<>();

    public static void set(List<Shape> shapes) {
        copied = shapes;
    }

    public static List<Shape> get() {
        return copied;
    }

    public static void clear() {
        copied = new ArrayList<>();
    }
}
