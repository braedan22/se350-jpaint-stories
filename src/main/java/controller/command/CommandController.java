package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.interfaces.UserChoices;
import model.picture.*;
import view.gui.PaintCanvas;
import java.util.List;

public class CommandController {
    private UserChoices userChoices;
    private static PaintCanvas canvas;

    public CommandController(PaintCanvas canvas, UserChoices userChoices) {
        this.userChoices = userChoices;
        CommandController.canvas = canvas;
    }

    public void onDraw(Point start, Point end) {
        Command command = new CreateShapeCommand(canvas, userChoices, start, end);
        command.run();
        CommandHistory.add((Undoable) command);
    }

    public void onSelect(Point start, Point end) {
        List<Shape> shapes = Picture.select(start, end);
        Selected.set(shapes);
        canvas.repaint();
    }

    public void onMove(Point start, Point end) {
        int deltaX = end.getX()-start.getX();
        int deltaY = end.getY()-start.getY();
        List<Shape> shapeCopy = Selected.get();
        Command command = new CreateMoveCommand(canvas, shapeCopy, deltaX, deltaY);
        command.run();
        CommandHistory.add((Undoable) command);
    }

    public static void onCopy(){
        List<Shape> copied = Selected.get();
        Copied.set(copied);
        System.out.println(copied);
    }

    public static void onPaste() {
        List<Shape> copied = Copied.get();
        Command command = new CreatePasteCommand(canvas, copied);
        command.run();
        CommandHistory.add((Undoable) command);
    }
}
