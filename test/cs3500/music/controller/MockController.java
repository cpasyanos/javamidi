package cs3500.music.controller;

import cs3500.music.view.MockGUIView;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A mock controller for testing purposes.
 */
public class MockController extends Controller {
  private StringBuffer sb;
  MockGUIView mockView;

  /**
   * Constructs a model and view based on the file and view type indicated in the arguments.
   * It also configures the mouse and keyboard listeners so the user may add notes and scroll
   * through the piece.
   *
   * @param filename the name of the file to be read.
   * @param viewType the type of view to use.
   * @param out the StringBuffer to write mocks to.
   * @throws IOException if the file is invalid.
   */
  public MockController(String filename, String viewType, StringBuffer out) throws IOException {
    super(filename, viewType);
    this.sb = out;

    mockView = new MockGUIView();

    configureMockListener();
  }

  /**
   * Configures the MockKeyListener for the MockView for the MockController.
   */
  private void configureMockListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<Integer,Runnable>();

    keyPresses.put(KeyEvent.VK_RIGHT, new MockScrollForward());
    keyPresses.put(KeyEvent.VK_LEFT, new MockScrollBack());
    keyPresses.put(KeyEvent.VK_SPACE, new MockTogglePause());
    keyPresses.put(KeyEvent.VK_END, new MockToEnd());
    keyPresses.put(KeyEvent.VK_HOME, new MockToStart());

    KeyHandler handler = new KeyHandler();

    handler.setKeyPressedMap(keyPresses);

    mockView.addKeyListener(handler);
  }

  /**
   * Returns the KeyListener of the mock view.
   * @return the KeyListener of the mock view.
   */
  public KeyListener getKeyListener() {
    return this.mockView.getKeyListener();
  }

  /**
   * Returns the JFrame of the mock view.
   * @return the JFrame of the mock view.
   */
  public JFrame getFrame() {
    return this.mockView.getFrame();
  }


  /**
   * A mock Runnable for scrolling forward.
   */
  private class MockScrollForward implements Runnable {

    @Override
    public void run() {
      sb.append("Scrolled forward.\n");
    }
  }

  /**
   * A mock Runnable for scrolling back.
   */
  private class MockScrollBack implements Runnable {

    @Override
    public void run() {
      sb.append("Scrolled back.\n");
    }
  }

  /**
   * A mock Runnable for changing pause states.
   */
  private class MockTogglePause implements Runnable {

    @Override
    public void run() {
      sb.append("Toggled pause.\n");
    }
  }

  /**
   * A mock Runnable for jumping to the end.
   */
  private class MockToEnd implements Runnable {

    @Override
    public void run() {
      sb.append("Jumped to end of piece.\n");
    }
  }

  /**
   * A mock Runnable for jumping to the start.
   */
  private class MockToStart implements Runnable {

    @Override
    public void run() {
      sb.append("Jumped to beginning of piece.\n");
    }
  }

  /**
   * A mock runnable for adding new notes.
   */
  private class MockAddNotes implements Runnable {

    @Override
    public void run() {
      sb.append("Added a new note.\n");
    }
  }
}
