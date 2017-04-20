package cs3500.music.model;

import java.util.List;

/**
 * This interface represents a single musical piece. A piece of music is a collection of notes
 * in a range of beats, pitches, and durations, and can be played by the music editor to produce
 * a melody. Implementations of this interface must override toString() in a meaningful way to
 * produce a text output of the piece as a whole.
 */
public interface IPiece {

  /**
   * Adds a new note with the given specifications to the piece of music.
   * @param pitch the pitch at which the note is played
   * @param octave the octave the pitch is in
   * @param start the beginning beat of the note
   * @param duration the duration of the note
   * @param volume the volume the note will be played at
   * @param instrument the instrument the note is played with
   * @throws IllegalArgumentException if the duration is less than 1 or the start is less than 0.
   */
  void addNote(Pitch pitch, int octave, int start, int duration, int volume, int instrument)
          throws IllegalArgumentException;

  /**
   * Removes an already existing note from the piece.
   * @param startOf the beat that the note begins at
   * @param pitch the pitch the note plays at
   * @param octave the octave the note is in
   * @throws IllegalArgumentException if either the start is out of bounds, or if no such note
   *         exists at the given beat.
   */
  void removeNote(int startOf, Pitch pitch, int octave) throws IllegalArgumentException;

  /**
   * Measures the size of the piece in beats.
   * @return how many beats the piece goes on for
   */
  int getNumBeats();

  /**
   * ADDED HW06: Retrieves the tempo that this piece is played at.
   * @return the tempo
   */
  int getTempo();
  
  /**
   * A method that retrieves the lowest note in the entire piece. Cannot be called on
   * an empty piece of music. Changed to public as it is useful to the view.
   * @return the lowest note
   * @throws IllegalStateException if the piece is currently empty
   */
  Note getLowestNote();
  
  /**
   * A method that retrieves the lowest note in the entire piece. Cannot be called on
   * an empty piece of music. Changed to public as it is useful to the view.
   * @return the lowest note
   * @throws IllegalStateException if the piece is currently empty
   */
  Note getHighestNote();

  /**
   * ADDED HW06: Gets the range of notes in the piece.
   * @return The range of notes
   */
  int getNoteRange();
  
  /**
   * ADDED HW06: Gets the list of beats.
   * @return the list of beats.
   */
  List<Beat> getBeats();

  /**
   * Increments the tempo by the given amount.
   * @param increment the amount to increase the tempo by.
   */
  void incrementTempo(int increment);

  /**
   * Decrements the tempo by the given amount.
   * @param decrement the amount to decrease the tempo by.
   */
  void decrementTempo(int decrement);
}
