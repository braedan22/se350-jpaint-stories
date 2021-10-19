package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.ShapeType;
import model.picture.*;
import model.picture.Point;
import model.picture.Rectangle;
import model.picture.Shape;
import view.gui.PaintCanvas;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateMoveCommand implements Command, Undoable{
    private PaintCanvas canvas;
    private List<Shape> oldShapes;
    private List<Shape> newShapes;
    private int deltaX;
    private int deltaY;

    public CreateMoveCommand(PaintCanvas canvas, List<Shape> shapes, int deltaX, int deltaY){
        this.canvas = canvas;
        this.oldShapes = shapes;
        this.newShapes =  new ArrayList<>();
        this.deltaX = deltaX;
        this.deltaY = deltaY;

        for(Shape oldShape:oldShapes){
            ShapeType shapeType = oldShape.getShapeType();
            Color color = oldShape.getColor();
            Point start = new Point(oldShape.getStart().getX()+deltaX, oldShape.getStart().getY()+deltaY);
            Point end = new Point(oldShape.getEnd().getX()+deltaX, oldShape.getEnd().getY()+deltaY);

            if (oldShape.getShapeType().equals(ShapeType.RECTANGLE)) {
                newShapes.add(new Rectangle(color, start, end));
            } else if (shapeType.equals(ShapeType.ELLIPSE)) {
                newShapes.add(new Ellipse(color, start, end));
            } else if (shapeType.equals(ShapeType.TRIANGLE)) {
                newShapes.add(new Triangle(color, start, end));
            }

        }
    }

    @Override
    public void run() {
        for(Shape shape:oldShapes){
            Picture.remove(shape);
        }
        for(Shape shape:newShapes){
            Picture.add(shape);
        }
        Selected.set(newShapes);
        canvas.repaint();
    }

    @Override
    public void undo() {
        for(Shape shape:newShapes){
            Picture.remove(shape);
        }
        for(Shape shape:oldShapes){
            Picture.add(shape);
        }
        Selected.set(oldShapes);
        canvas.repaint();
    }

    @Override
    public void redo() {
        this.run();
    }
}
