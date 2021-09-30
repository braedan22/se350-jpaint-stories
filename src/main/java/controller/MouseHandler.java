package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MouseHandler is responsible for propagating mouse coordinates into our application
 * classes. This is a boundary class so very little code should be added here.
 */
public class MouseHandler extends MouseAdapter {

  private static final Logger log = LoggerFactory.getLogger(MouseHandler.class);

  @Override
  public void mousePressed(MouseEvent e) {
    log.debug("Start " + e.getX() + " " + e.getY());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    log.debug("End " + e.getX() + " " + e.getY());
  }
}
