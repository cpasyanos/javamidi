package cs3500.music.model;

import java.util.List;
import java.util.Objects;

/**
 * The ViewModel implementation of IPiece. It has an ArrayList of beats, each containing a list of
 * Notes that begin at that beat. The toString override produces a textual output of the music
 * piece. This Implementation is ReadOnly for the piece.
 */
public class ViewModel implements IPiece {
  IPiece piece;
  
  /**
   * Constructs and initially empty ViewModel.
   */
  public ViewModel() {
    piece = null;
  }
  
  /**
   * gives the ViewModel a Piece to use.
   * @param p the piece to be used by the ViewModel
   */
  public void assignPiece(IPiece p) {
    if (p.getClass().equals(Piece.class)) {
      this.piece = p;
    }
    else {
      throw new IllegalArgumentException("Cannot give a ViewModel a ViewModel.");
    }
  }
  
  @Override
  public void addNote(Pitch pitch, int octave, int start, int duration, int volume,
                      int instrument) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This is Read Only.");
  }
  
  @Override
  public void removeNote(int startOf, Pitch pitch, int octave)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This is Read Only.");
  }
  
  @Override
  public int getNumBeats() {
    Objects.requireNonNull(piece);
    return piece.getNumBeats();
  }
  
  @Override
  public int getTempo() {
    Objects.requireNonNull(piece);
    return piece.getTempo();
  }
  
  @Override
  public Note getLowestNote() {
    Objects.requireNonNull(piece);
    return piece.getLowestNote();
  }
  
  @Override
  public Note getHighestNote() {
    Objects.requireNonNull(piece);
    return piece.getHighestNote();
  }

  @Override
  public int getNoteRange() {
    Objects.requireNonNull(piece);
    return piece.getNoteRange();
  }
  
  @Override
  public List<Beat> getBeats() {
    Objects.requireNonNull(piece);
    return piece.getBeats();
  }

  @Override
  public String toString() {
    Objects.requireNonNull(piece);
    return piece.toString();
  }
}
