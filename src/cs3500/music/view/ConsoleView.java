package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * A static text view of the piece, outputted to the compiler console.
 */
public class ConsoleView implements IView {
  private IPiece piece;
  private String consoleText;
  
  public ConsoleView() {
    this.piece = null;
    this.consoleText = "";
  }
  
  @Override
  public void assignPiece(IPiece piece) {
    this.piece = piece;
    this.consoleText = piece.toString();
  }
  
  
  @Override
  public void show(int beat) {
    System.out.print(consoleText);
  }

  @Override
  public void scrollForward() {
    throw new UnsupportedOperationException("Not supported by console view.");
  }

  @Override
  public void scrollBack() {
    throw new UnsupportedOperationException("Not supported by console view.");
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    // do nothing: this is a static view.
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    // do nothing: again, a static view.
  }

  @Override
  public void togglePause() {
    // do nothing: this is a static view
  }

  @Override
  public Note getNoteOnPiano() {
    // does nothing in a static view.
    return null;
  }

  @Override
  public void update() {
    consoleText = piece.toString();
  }
}
