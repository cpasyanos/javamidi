package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.ProviderModel;
import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.view.TextualView;

/**
 * The concrete adapter of our provider's console view to our view interface.
 */
public class ProviderConsoleView extends AProviderView {

  @Override
  public void assignPiece(IPiece model) {
    MusicModel providerModel = new ProviderModel(model);

    this.view = new TextualView(providerModel);
  }
}
