package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.interfaces.UserChoices;
import model.picture.Picture;
import model.picture.Point;
import model.picture.Selected;
import model.picture.Shape;
import view.gui.PaintCanvas;
import java.util.List;

public class CommandController {
    private UserChoices userChoices;
    private PaintCanvas canvas;

    public CommandController(PaintCanvas canvas, UserChoices userChoices) {
        this.userChoices = userChoices;
        this.canvas = canvas;
    }

    public void onDraw(Point start, Point end) {
        Command command = new CreateShapeCommand(canvas, userChoices, start, end);
        command.run();
        CommandHistory.add((Undoable) command);
    }

    public void onSelect(Point start, Point end) {
        List<Shape> shapes = Picture.select(start, end);
        Selected.set(shapes);
    }

    public void onMove(Point start, Point end) {
        int deltaX = end.getX()-start.getX();
        int deltaY = end.getY()-start.getY();
        List<Shape> shapeCopy = Selected.get();
        Command command = new CreateMoveCommand(canvas, shapeCopy, deltaX, deltaY);
        command.run();
        CommandHistory.add((Undoable) command);
    }
}
