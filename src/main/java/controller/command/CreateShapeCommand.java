package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.ShapeType;
import model.interfaces.UserChoices;
import model.picture.Picture;
import model.picture.Point;
import model.picture.Rectangle;
import model.picture.Shape;
import java.awt.*;

public class CreateShapeCommand implements Command, Undoable{
    private Shape shape;

    public CreateShapeCommand(UserChoices userChoices, Point start, Point end) {
        ShapeType shapeType = userChoices.getActiveShapeType();
        Color color = userChoices.getActivePrimaryColor().value;
        if (shapeType.equals(ShapeType.RECTANGLE)) {
            this.shape = new Rectangle(color, start, end);
        }
    }

    @Override
    public void run() {
        Picture.add(shape);
    }

    @Override
    public void undo() {
        Picture.remove(shape);
    }

    @Override
    public void redo() {
        Picture.add(shape);
    }
}
