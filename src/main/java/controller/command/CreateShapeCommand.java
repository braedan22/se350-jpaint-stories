package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.interfaces.UserChoices;
import model.picture.Point;

public class CreateShapeCommand implements Command, Undoable{

    public CreateShapeCommand(UserChoices userChoices, Point start, Point end) {

    }

    @Override
    public void run() {
        System.out.println("running...");
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
