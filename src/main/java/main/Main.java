package main;

import controller.EventConnector;
import controller.EventConnectorImpl;
import controller.KeyboardInterface;
import controller.MouseHandler;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import model.persistence.UserChoicesImpl;
import view.gui.Gui;
import view.gui.GuiWindowImpl;
import view.gui.PaintCanvas;
import view.interfaces.GuiWindow;
import view.interfaces.UiModule;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PaintCanvas paintCanvas = new PaintCanvas();
        GuiWindow guiWindow = new GuiWindowImpl(paintCanvas);
        UiModule uiModule = new Gui(guiWindow);
        UserChoicesImpl appState = new UserChoicesImpl(uiModule);
        EventConnector controller = new EventConnectorImpl(uiModule, appState);

        KeyboardInterface keys = new KeyboardInterface(paintCanvas, appState);
        keys.setup();

        MouseHandler mouse = new MouseHandler();
        paintCanvas.addMouseListener(mouse);
        controller.setup();

        Thread.sleep(500);

        Graphics2D graphics2d = paintCanvas.getGraphics2D();

        // - Begin example: remove after you understand it

        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(12, 13, 200, 400);

        // Outlined rectangle
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(12, 13, 200, 400);

        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);

        // - End example
    }
}
