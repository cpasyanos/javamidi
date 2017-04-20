package cs3500.music.view;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * Creates the piano key graphic in the bottom part of the gui view.
 */
class PianoGraphic extends JPanel {
  private IPiece piece;
  private int currBeat;
  private ArrayList<Note> currNotes;
  private List<Note> noteList;
  
  /**
   * Creates a new piano graphic given the piece.
   * @param p the piece to be displayed.
   * @param notes a list of all the notes in the piece.
   */
  PianoGraphic(IPiece p, List<Note> notes) {
    this.piece = p;
    this.currBeat = 0;
    this.currNotes = new ArrayList<>();
    this.noteList = notes;
    for (Note n: this.noteList) {
      if ((n.getFirstBeatOf() <= currBeat)
              && ((n.getFirstBeatOf() + n.getDuration() - 1) >= currBeat)) {
        currNotes.add(n);
      }
    }
  }

  /**
   * Scrolls the current beat forward as long as the incremented beat will still be in the
   * range of the piece and creates a list of all the notes that are currently playing.
   */
  void scrollForward() {
    if (currBeat <= (piece.getNumBeats()) - 1) {
      currBeat++;
    }
    currNotes.clear();
    for (Note n: this.noteList) {
      if ((n.getFirstBeatOf() <= currBeat)
              && ((n.getFirstBeatOf() + n.getDuration() - 2) >= currBeat)) {
        currNotes.add(n);
      }
    }
  }

  /**
   * Scrolls the current beat back as long as the decremented beat will still be in the range of
   * the piece and creates a list of all the notes that are currently playing.
   */
  void scrollBack() {
    if (currBeat > 0) {
      currBeat--;
    }
    currNotes.clear();
    for (Note n: this.noteList) {
      if ((n.getFirstBeatOf() <= currBeat)
              && ((n.getFirstBeatOf() + n.getDuration() - 2) >= currBeat)) {
        currNotes.add(n);
      }
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
    g.setColor(Color.WHITE);
    g.fillRect(0,0,1200,600);
    int tempInt;
    int sharpOffset  = 480;
    int natKey;
    for (int i = 0; i < 70; i++) {
      g.setColor(Color.black);
      g.drawRect((i * 1200 / 70), 0, 1200 / 70, 400);
      g.setColor(Color.black);
    }
    
    g.setColor(Color.yellow);
    
    //g.fillRect(70, 0, 1200 / 70, 400);
  
    for (Note n: currNotes) {
      g.setColor(Color.cyan);
      switch (n.getPitch().toString()) {
        case "C":
          natKey = 0;
          break;
        case "D":
          natKey = 1;
          break;
        case "E":
          natKey = 2;
          break;
        case "F":
          natKey = 3;
          break;
        case "G":
          natKey = 4;
          break;
        case "A":
          natKey = 5;
          break;
        case "B":
          natKey = 6;
          break;
        default:
          natKey = -1;
          break;
      }
      
      if (natKey != -1) {
        g.fillRect((n.getOctave() * 120) + (natKey * (1200 / 70)), 0, 1200 / 70, 400);
        g.setColor(Color.black);
        g.drawRect((n.getOctave() * 120) + (natKey * (1200 / 70)), 0, 1200 / 70, 400);
        g.setColor(Color.black);
      }
    }
    
    g.setColor(Color.BLACK);
    for (int i = 0; i < 70; i++) {
      tempInt = i % 7;
      if ((tempInt == 1) || (tempInt == 2) || (tempInt == 4) || (tempInt == 5) || (tempInt == 6)) {
        g.fillRect((i * 1200 / 70) - ((1200 / 70) / 4), 0, (1200 / 70) / 2, 200);
      }
      g.setColor(Color.black);
    }
  
    for (Note n: currNotes) {
      g.setColor(Color.cyan);
      tempInt = n.getMidiVal() % 12;
      if ((tempInt == 1) || (tempInt == 3) || (tempInt == 6) || (tempInt == 8) || (tempInt == 10)) {
        g.fillRect(((n.getMidiVal() + 1) * 1200 / 70) - ((1200 / 70) / 4) - sharpOffset,
                0, (1200 / 140), 200);
        g.setColor(Color.black);
        g.drawRect(((n.getMidiVal() + 1) * 1200 / 70) - ((1200 / 70) / 4) - sharpOffset,
                0, 1200 / 140, 200);
      }
    }
  }

  /**
   * Retrieves the note of the piano where the mouse currently is.
   * @return that note
   */
  Note getClickedNote() {
    // where the mouse is:
    Point curr = this.getMousePosition();
    double x = curr.getX();
    double y = curr.getY();

    int startBeat = currBeat;
    int duration = 1;

    // default values for instrument and volume: can be tweaked
    int instrument = 0;
    int volume = 70;

    int octave = (int) x / 120;

    Pitch pitch = null;

    if (y > 200) {
      int noteNum = (int) (7.0 * (( x % 120.0) / 120.0));
      switch (noteNum) {
        case 0:
          pitch = Pitch.C;
          break;
        case 1:
          pitch = Pitch.D;
          break;
        case 2:
          pitch = Pitch.E;
          break;
        case 3:
          pitch = Pitch.F;
          break;
        case 4:
          pitch = Pitch.G;
          break;
        case 5:
          pitch = Pitch.A;
          break;
        case 6:
          pitch = Pitch.B;
          break;
        default:
          //should never happen
          break;
      }
    }

    else if (y <= 200) {
      int noteNum = (int) (28.0 * (( x % 120.0) / 120.0));
      switch (noteNum) {
        case 0:
          pitch = Pitch.C;
          break;
        case 1:
          pitch = Pitch.C;
          break;
        case 2:
          pitch = Pitch.C;
          break;
        case 3:
          pitch = Pitch.CSHARP;
          break;
        case 4:
          pitch = Pitch.CSHARP;
          break;
        case 5:
          pitch = Pitch.D;
          break;
        case 6:
          pitch = Pitch.D;
          break;
        case 7:
          pitch = Pitch.DSHARP;
          break;
        case 8:
          pitch = Pitch.DSHARP;
          break;
        case 9:
          pitch = Pitch.E;
          break;
        case 10:
          pitch = Pitch.E;
          break;
        case 11:
          pitch = Pitch.E;
          break;
        case 12:
          pitch = Pitch.F;
          break;
        case 13:
          pitch = Pitch.F;
          break;
        case 14:
          pitch = Pitch.F;
          break;
        case 15:
          pitch = Pitch.FSHARP;
          break;
        case 16:
          pitch = Pitch.FSHARP;
          break;
        case 17:
          pitch = Pitch.G;
          break;
        case 18:
          pitch = Pitch.G;
          break;
        case 19:
          pitch = Pitch.GSHARP;
          break;
        case 20:
          pitch = Pitch.GSHARP;
          break;
        case 21:
          pitch = Pitch.A;
          break;
        case 22:
          pitch = Pitch.A;
          break;
        case 23:
          pitch = Pitch.ASHARP;
          break;
        case 24:
          pitch = Pitch.ASHARP;
          break;
        case 25:
          pitch = Pitch.B;
          break;
        case 26:
          pitch = Pitch.B;
          break;
        case 27:
          pitch = Pitch.B;
          break;
        default:
          //should never happen
          break;
      }
    }

    return new Note(duration, startBeat, octave, pitch, volume, instrument);
  }

  /**
   * Updates the list of notes to be synchronized with the given list of notes.
   * @param notes the list of notes in the piece.
   */
  void updateList(List<Note> notes) {
    this.noteList.clear();
    this.noteList = notes;

    currNotes.clear();


    for (Note n: this.noteList) {
      if ((n.getFirstBeatOf() <= currBeat)
              && ((n.getFirstBeatOf() + n.getDuration() - 2) >= currBeat)) {
        currNotes.add(n);
      }
    }

    for (Note n: this.currNotes) {
      System.out.print(n.toString() + " ");
    }

    System.out.println("updated");
  }
}
