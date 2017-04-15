package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;
import cs3500.music.model.ProviderModel;
import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.view.MidiViewImpl;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * The concrete adapter of our provider's midi view to our view interface.
 */
public class ProviderMidiView extends AProviderView {

  @Override
  public void assignPiece(IPiece model) {
    MusicModel providerModel = new ProviderModel(model);

    try {
      this.view = new MidiViewImpl(providerModel.map(), providerModel.getTempo(), 127,
              MidiSystem.getSequencer());
    } catch (MidiUnavailableException e) {
      // do nothing
    }
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
