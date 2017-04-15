package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
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
    Objects.requireNonNull(view);

    try {
      view.initialize();
    } catch (InvalidMidiDataException e) {
      // do nothing
    } catch (MidiUnavailableException e) {
      // do nothing
    }
  }
}
