package cs3500.music.view;

import cs3500.music.controller.KeyHandler;

import javax.swing.JFrame;
import java.awt.event.KeyListener;

/**
 * A mock of the gui view to test handlers.
 */
public class MockGUIView {
  JFrame handlerHolder;

  public MockGUIView() {
    this.handlerHolder = new JFrame();
    handlerHolder.setVisible(false);
  }

  public void addKeyListener(KeyHandler keyHandler) {
    handlerHolder.addKeyListener(keyHandler);
  }

  public KeyListener getKeyListener() {
    return handlerHolder.getKeyListeners()[0];
  }

  public JFrame getFrame() {
    return this.handlerHolder;
  }


}
