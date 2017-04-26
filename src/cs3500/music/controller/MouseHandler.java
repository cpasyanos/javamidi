package cs3500.music.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * A class that handles user mouse clicks allowing them to add notes by clicking.
 */
public class MouseHandler extends MouseAdapter {
  private Map<Integer, Runnable> runnableMap;

  /**
   * Constructs a mousehandler with an empty map.
   */
  public MouseHandler() {
    this.runnableMap = new HashMap<Integer, Runnable>();
  }

  /**
   * Adds a map of mouse presses and runnables to the map.
   * @param mousePressedMap the map of runnables and mouse presses.
   */
  public void setRunnableMap(Map<Integer, Runnable> mousePressedMap) {
    this.runnableMap = mousePressedMap;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    runnableMap.get(0).run();
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
    runnableMap.get(1).run();
  }
  
}
