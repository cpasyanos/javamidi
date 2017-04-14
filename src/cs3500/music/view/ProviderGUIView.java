package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * The concrete adapter of our provider's gui view to our view interface.
 */
public class ProviderGUIView extends AProviderView {

  @Override
  public void show(int beat) {

  }

  @Override
  public void scrollForward() {

  }

  @Override
  public void scrollBack() {

  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }

  @Override
  public void addMouseListener(MouseListener listener) {

  }

  @Override
  public void togglePause() {

  }

  @Override
  public Note getNoteOnPiano() {
    return null;
  }

  @Override
  public void update() {

  }
}
