package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Font;

/**
 * Creates the top side list of notes in the piece for the gui view.
 */
class NoteGraphic extends Canvas {
  IPiece piece;

  /**
   * Constructs the panel with the given piece.
   * @param p the piece.
   */
  NoteGraphic(IPiece p) {
    this.piece = p;
  }

  @Override
  public void paint(Graphics g) {
    // the note this method is currently working on
    Note currNote = piece.getHighestNote();
    
    g.setFont(new Font("IDK", Font.BOLD, (550 / piece.getNoteRange())));
    
    for (int i = 1; i <= piece.getNoteRange(); i++) {
      g.drawString(currNote.toString(), 0, ((550 / piece.getNoteRange()) * i) + 49);
      //this is for testing vertical alignment.
      //g.drawLine(0,((550 / piece.getNoteRange()) * i)
      //      + 49, 100, ((550 / piece.getNoteRange()) * i) + 49);
      currNote = currNote.getPreviousNote();
    }
  }
}
