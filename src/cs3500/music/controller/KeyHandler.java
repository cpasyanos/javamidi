package cs3500.music.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that handles all user keyboard inputs.
 */
public class KeyHandler extends KeyAdapter {
  private Map<Integer, Runnable> keyPressedMap;

  /**
   * Constructs a key handler, gives the key handler the gui view.
   */
  public KeyHandler() {
    this.keyPressedMap = new HashMap<Integer, Runnable>();
  }

  @Override
  public void keyPressed(KeyEvent e) {

    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  /**
   * Sets a map of key codes and runnables to the key pressed map.
   * @param keyPressedMap the map.
   */
  public void setKeyPressedMap(Map<Integer, Runnable> keyPressedMap) {
    this.keyPressedMap = keyPressedMap;
  }
}
