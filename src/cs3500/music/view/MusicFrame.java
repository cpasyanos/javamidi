package cs3500.music.view;

import cs3500.music.model.IPiece;

import javax.swing.JFrame;

/**
 * The JFrame that holds the gui.
 */
class MusicFrame extends JFrame {
  IPiece piece;

  /**
   * Sets the displayed piece to the given viewmodel.
   * @param piece the read-only viewmodel
   */
  public void setPiece(IPiece piece) {
    this.piece = piece;
  }
}
