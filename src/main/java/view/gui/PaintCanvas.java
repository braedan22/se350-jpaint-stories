package view.gui;

import javax.swing.JComponent;
import java.awt.*;

import model.picture.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PaintCanvas is responsible for responding to the graphics system when it
 * is time to update the display.  This is a boundary class so very little code
 * should be added here.
 */
public class PaintCanvas extends JComponent {
    private static final Logger log = LoggerFactory.getLogger(PaintCanvas.class);

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Picture.draw(graphics);
    }
}
