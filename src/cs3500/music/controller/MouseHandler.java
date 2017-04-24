package cs3500.music.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that handles user mouse clicks allowing them to add notes by clicking.
 */
public class MouseHandler extends MouseAdapter {
  private Map<Integer, Runnable> mousePressedMap;

  /**
   * Constructs a mousehandler with an empty map.
   */
  public MouseHandler() {
    this.mousePressedMap = new HashMap<Integer, Runnable>();
  }

  /**
   * Adds a map of mouse presses and runnables to the map.
   * @param mousePressedMap the map of runnables and mouse presses.
   */
  public void setMousePressedMap(Map<Integer, Runnable> mousePressedMap) {
    this.mousePressedMap = mousePressedMap;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // System.out.println("e = [" + e.getButton() + "]");
    if (mousePressedMap.containsKey(e.getButton())) {
      mousePressedMap.get(e.getButton()).run();
    }
  }
}
