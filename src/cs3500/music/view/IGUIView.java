package cs3500.music.view;

/**
 * A graphical-specific view interface of the piece.
 */
public interface IGUIView extends IView {
  /**
   * ADDED HW07: Sets the focus back to the main JFrame.
   */
  void resetFocus();
}
