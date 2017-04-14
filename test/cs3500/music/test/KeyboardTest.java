package cs3500.music.test;

import cs3500.music.controller.MockController;
import org.junit.Test;

import javax.swing.JFrame;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Tests the key handler on a mock runnable.
 */
public class KeyboardTest {

  @Test
  public void testKeyHandler() {
    StringBuffer sb = new StringBuffer();

    try {
      MockController controller = new MockController("mary-little-lamb.txt", "console", sb);

      KeyListener kl = controller.getKeyListener();

      JFrame source = controller.getFrame();

      kl.keyPressed(new KeyEvent(source, KeyEvent.KEY_PRESSED,
              1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
      kl.keyPressed(new KeyEvent(source, KeyEvent.KEY_PRESSED,
              1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
      kl.keyPressed(new KeyEvent(source, KeyEvent.KEY_PRESSED,
              1, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
      kl.keyPressed(new KeyEvent(source, KeyEvent.KEY_PRESSED,
              1, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
      kl.keyPressed(new KeyEvent(source, KeyEvent.KEY_PRESSED,
              1, 0, KeyEvent.VK_SPACE, ' '));
    }
    catch (IOException e) {
      //shouldn't happen, doesn't really matter anyway.
    }

    assertEquals(sb.toString(), "Scrolled forward.\n"
            + "Scrolled back.\n"
            + "Jumped to end of piece.\n"
            + "Jumped to beginning of piece.\n"
            + "Toggled pause.\n");

  }
}
