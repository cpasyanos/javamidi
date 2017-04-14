package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * An interface for creating views.
 */
public interface IView {
  /**
   * assigns the ViewModel to the view.
   * @param piece the ViewModel to be assigned.
   */
  void assignPiece(IPiece piece);
  
  /**
   * Shows the piece as is appropriate to the view in question.
   */
  void show(int beat);

  // these two were added to this interface rather than IGUIView as MidiView needed to
  // implement it.
  /**
   * scrolls the view forward.
   */
  void scrollForward();

  /**
   * scrolls the view backwards.
   */
  void scrollBack();

  // this was also added to this interface as the controller calls it on any view.
  /**
   * Adds a listener for keyboard input to the gui view.
   * @param listener the KeyListener object
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds a listener for mouse input to the gui view.
   * @param listener the MouseListener object
   */
  void addMouseListener(MouseListener listener);

  /**
   * If the view is paused, play it. If the view is playing, pause it.
   */
  void togglePause();

  /**
   * Retrieves the note (with a duration of 1) of the piano key at the current mouse position.
   * @return the note.
   */
  Note getNoteOnPiano();

  /**
   * Updates the view to ensure it is synchronized with the model.
   */
  void update();

}
