package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.command.CommandController;
import model.MouseMode;
import model.interfaces.UserChoices;
import model.picture.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MouseHandler is responsible for propagating mouse coordinates into our application
 * classes. This is a boundary class so very little code should be added here.
 */
public class MouseHandler extends MouseAdapter {
    private CommandController commandController;
    private UserChoices userChoices;

    public MouseHandler(CommandController commandController, UserChoices userChoices) {
        this.commandController = commandController;
        this.userChoices = userChoices;
    }

    private static final Logger log = LoggerFactory.getLogger(MouseHandler.class);
    private Point start;

    @Override
    public void mousePressed(MouseEvent e) {
        start = new Point(e.getX(), e.getY());
        log.debug("Start " + e.getX() + " " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point end = new Point(e.getX(), e.getY());
        log.debug("End " + e.getX() + " " + e.getY());
        if (userChoices.getActiveMouseMode().equals(MouseMode.DRAW)) {
            commandController.onDraw(start, end);
        }
    }
}
