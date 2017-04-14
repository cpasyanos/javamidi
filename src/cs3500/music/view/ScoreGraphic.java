package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.List;

/**
 * Creates the top part of the gui that displays the piece's score.
 */
class ScoreGraphic extends JPanel {
  private IPiece piece;
  private List<Note> noteList;
  private int currBeat;

  /**
   * Creates the score graphic given the list of notes from the gui view and the piece they came
   * from.
   * @param p the piece
   * @param notes the list of notes
   */
  ScoreGraphic(IPiece p, List<Note> notes) {
    this.piece = p;
    this.currBeat = 0;
    this.noteList = notes;
  }

  /**
   * Scrolls the current beat forward as long as the incremented beat will still be in the
   * range of the piece.
   */
  void scrollForward() {
    if (currBeat <= (piece.getNumBeats()) - 1) {
      currBeat++;
    }
  }

  /**
   * Scrolls the current beat back as long as the decremented beat will still be in the range of
   * the piece.
   */
  void scrollBack() {
    if (currBeat > 0) {
      currBeat--;
    }
  }
  
  /**
   * sets the current beat to n.
   * @param n the number to set the current beat to.
   */
  void setCurrBeat(int n) {
    this.currBeat = n;
  }
  
  @Override
  public void paint(Graphics g) {
    int noteRange = piece.getNoteRange();
    int pieceLength = piece.getNumBeats();

    // the note this method is currently working on
    Note currNote = piece.getHighestNote();

    g.setFont(new Font("IDK", Font.BOLD, (550 / noteRange)));
  
    g.drawLine(0, 42, 25 * pieceLength, 42);
    
    for (int i = 1; i <= noteRange; i++) {
      currNote = currNote.getPreviousNote();
      g.drawLine(0, (((550 / noteRange) * i) + 42), 25 * pieceLength,
              (((550 / noteRange) * i) + 42));
    }
    
    int notePosn;

    for (int i = 0; i < pieceLength; i++) {
      if (i % 4 == 0) {
        g.setColor(Color.black);
        g.setFont(new Font("IDK", Font.BOLD, 20));
        g.drawString(Integer.toString(i), i * 25, 30);
        g.drawLine(i * 25 - 1, 42, i * 25 - 1, ((550 / noteRange)
                * (noteRange - 1)) + (550 / noteRange) + 42);
        g.drawLine(i * 25, 42, i * 25, ((550 / noteRange)
                * (noteRange - 1)) + (550 / noteRange) + 42);
        g.drawLine(i * 25 + 1, 42, i * 25 + 1, ((550 / noteRange)
                * (noteRange - 1)) + (550 / noteRange) + 42);
        g.setFont(new Font("IDK", Font.BOLD, (550 / noteRange)));
      }
    }

    int highNotePosn = piece.getHighestNote().getMidiVal();

    for (Note n: noteList) {
      int firstBeat = n.getFirstBeatOf();
      notePosn = (highNotePosn - n.getMidiVal());
      g.setColor(Color.BLACK);
      g.fillRect((firstBeat * 25), ((550 / noteRange * notePosn) + 42) + 1,25,
              (550 / noteRange) - 1);
      if (n.getDuration() > 1) {
        g.setColor(Color.GREEN);
        int beatsRemain = n.getDuration() - 2;
        g.fillRect(((firstBeat + 1) * 25), ((550 / noteRange * notePosn) + 42) + 1,
                25 * beatsRemain, (550 / noteRange) - 1);
      }
    }

    g.setColor(Color.RED);
    g.fillRect(25 * currBeat,42, 3,
            ((550 / noteRange) * (noteRange - 1))
                    + (550 / noteRange) + 42);
  }


  /**
   * Updates the list of notes to be synchronized with the given list of notes.
   * @param notes the list of notes in the piece.
   */
  void updateList(List<Note> notes) {
    this.noteList = notes;
  }
}
