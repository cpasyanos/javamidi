package cs3500.music.view;

// import cs3500.music.model.IPiece;
// import cs3500.music.provider.view.IView;


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
}
