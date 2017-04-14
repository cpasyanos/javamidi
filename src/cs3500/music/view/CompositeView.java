package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * A combination of the midi audio and gui visual view. This view supports pause and play,
 * scrolling (beat by beat or directly to the beginning or end), and adding notes.
 */
public class CompositeView implements IGUIView {
  private MidiView midiView;
  private GUIView guiView;
    
  public CompositeView() {
    this.midiView = new MidiView();
    this.guiView = new GUIView();
  }

  @Override
  public void scrollForward() {
    guiView.scrollForward();
  }

  @Override
  public void scrollBack() {
    guiView.scrollBack();
  }

  @Override
  public void resetFocus() {
    guiView.resetFocus();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    guiView.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    guiView.addMouseListener(listener);
  }

  @Override
  public void togglePause() {
    midiView.togglePause();
    guiView.togglePause();
  }

  @Override
  public Note getNoteOnPiano() {
    return guiView.getNoteOnPiano();
  }

  @Override
  public void update() {
    midiView.update();
    guiView.update();
  }

  @Override
  public void assignPiece(IPiece piece) {
    midiView.assignPiece(piece);
    guiView.assignPiece(piece);
  }

  @Override
  public void show(int beat) {
    guiView.show(beat);
    midiView.show(beat);
  }
}
