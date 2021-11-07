package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.picture.Picture;
import model.picture.Shape;
import view.gui.PaintCanvas;

import java.util.List;

public class CreateDeleteCommand implements Command, Undoable {
    PaintCanvas canvas;
    private List<Shape> deletes;

    public CreateDeleteCommand(PaintCanvas canvas, List<Shape> deletes){
        this.canvas = canvas;
        this.deletes = deletes;
    }

    @Override
    public void run() {
        for(Shape d:deletes) {
            Picture.remove(d);
        }
        canvas.repaint();
    }

    @Override
    public void undo() {
        for(Shape d:deletes) {
            Picture.add(d);
        }
        canvas.repaint();
    }

    @Override
    public void redo() {
        this.run();
    }
}
