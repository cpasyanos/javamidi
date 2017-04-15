package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.ProviderModel;
import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.view.GuiViewFrame;

/**
 * The concrete adapter of our provider's gui view to our view interface.
 */
public class ProviderGUIView extends AProviderView {

  @Override
  public void assignPiece(IPiece model) {
    MusicModel providerModel = new ProviderModel(model);

    this.view = new GuiViewFrame(providerModel.map(), providerModel.map().size());
  }
}
