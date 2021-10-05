package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.interfaces.UserChoices;
import model.picture.Point;

public class CommandController {
    private UserChoices userChoices;

    public CommandController(UserChoices userChoices) {
        this.userChoices = userChoices;
    }

    public void onDraw(Point start, Point end) {
        Command command = new CreateShapeCommand(userChoices, start, end);
        command.run();
        CommandHistory.add((Undoable) command);
    }
}
