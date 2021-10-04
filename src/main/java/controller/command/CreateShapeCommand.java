package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.interfaces.UserChoices;
import model.picture.Picture;
import model.picture.Point;
import model.picture.Rectangle;
import model.picture.Shape;
import java.awt.*;

public class CreateShapeCommand implements Command, Undoable{
    private Color color;
    private Point start;
    private Point end;

    public CreateShapeCommand(UserChoices userChoices, Point start, Point end) {
        this.color = userChoices.getActivePrimaryColor().value;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.println("running...");
        Shape shape = new Rectangle(color, start, end);
        Picture.add(shape);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
