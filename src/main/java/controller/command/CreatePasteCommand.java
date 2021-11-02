package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.ShapeShadingType;
import model.picture.Picture;
import model.picture.Point;
import model.picture.Rectangle;
import model.picture.Shape;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatePasteCommand implements Command, Undoable {
    private PaintCanvas canvas;
    private List<Shape> pasteShapes;

    public CreatePasteCommand(PaintCanvas canvas, List<Shape> copiedShapes) {
        this.canvas = canvas;
        this.pasteShapes = new ArrayList<>();
        for(Shape copiedShape:copiedShapes) {
            Shape newShape = copiedShape.clone();
            newShape.setStart(new Point(copiedShape.getStart().getX()+20, copiedShape.getStart().getY()+20));
            newShape.setEnd(new Point(copiedShape.getEnd().getX()+20, copiedShape.getEnd().getY()+20));
            pasteShapes.add(newShape);
        }
    }

    @Override
    public void run() {
        for(Shape shape:pasteShapes) {
            Picture.add(shape);
            System.out.println(shape + ": " + shape.getStart() + shape.getEnd());
        }
        canvas.repaint();
    }

    @Override
    public void undo() {
        for(Shape shape:pasteShapes) {
            Picture.remove(shape);
        }
        canvas.repaint();
    }

    @Override
    public void redo() {
        this.run();
    }
}
