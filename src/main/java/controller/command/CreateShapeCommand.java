package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.UserChoices;
import model.picture.*;
import model.picture.Point;
import model.picture.Rectangle;
import model.picture.Shape;
import view.gui.PaintCanvas;
import java.awt.*;

public class CreateShapeCommand implements Command, Undoable {
    private PaintCanvas canvas;
    private Shape shape;

    public CreateShapeCommand(PaintCanvas canvas, UserChoices userChoices, Point start, Point end) {
        this.canvas = canvas;
        ShapeType shapeType = userChoices.getActiveShapeType();
        Color primary = userChoices.getActivePrimaryColor().value;
        Color secondary = userChoices.getActiveSecondaryColor().value;
        ShapeShadingType treatment = userChoices.getActiveShapeShadingType();

        if (shapeType.equals(ShapeType.RECTANGLE)) {
            shape = new Rectangle(start, end, primary, secondary, treatment);
        } else if (shapeType.equals(ShapeType.ELLIPSE)) {
            shape = new Ellipse(start, end, primary, secondary, treatment);
        } else if (shapeType.equals(ShapeType.TRIANGLE)) {
            shape = new Triangle(start, end, primary, secondary, treatment);
        }
    }

    @Override
    public void run() {
        Picture.add(shape);
        canvas.repaint();
    }

    @Override
    public void undo() {
        Picture.remove(shape);
        canvas.repaint();
    }

    @Override
    public void redo() {
        Picture.add(shape);
        canvas.repaint();
    }
}
