package cs3500.music.view;

import cs3500.music.model.Note;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * An abstract adapter from our provider's view to our view.
 */
public abstract class AProviderView implements cs3500.music.view.IView {
  protected cs3500.music.provider.view.IView view;


  /**
   * The constructor takes a provider view and assigns it to the adapter.
   */
  public AProviderView() {
    this.view = null;
  }

  @Override
  public void show(int beat) {
    if (beat == 0) {

      Objects.requireNonNull(view);

      try {
        view.initialize();
      } catch (InvalidMidiDataException e) {
        // do nothing
      } catch (MidiUnavailableException e) {
        // do nothing
      }
    }
    else {
      //do nothing
    }
  }

  @Override
  public void scrollForward() {
    // do nothing
  }

  @Override
  public void scrollBack() {
    // do nothing
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    // do nothing
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    // do nothing
  }

  @Override
  public void togglePause() {
    // do nothing
  }

  @Override
  public Note getNoteOnPiano() {
    return null;
  }

  @Override
  public void update() {
    // do nothing
  }

}
