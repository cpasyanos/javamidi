package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.ProviderModel;
import cs3500.music.provider.model.MusicModel;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

/**
 * The concrete adapter of our provider's composite view to our view interface.
 */
public class ProviderCompositeView extends AProviderView {

  @Override
  public void assignPiece(IPiece model) {
    MusicModel providerModel = new ProviderModel(model);

    try {
      this.view = new cs3500.music.provider.view.CompositeView(providerModel,
              MidiSystem.getSequencer());
    } catch (MidiUnavailableException e) {
      // do nothing
    }
  }
}
