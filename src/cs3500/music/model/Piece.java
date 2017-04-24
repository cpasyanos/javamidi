package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.CompositionBuilder;

/**
 * The implementation of IPiece. It has an ArrayList of beats, each containing a list of Notes that
 * begin at that beat. The toString override produces a textual output of the music piece.
 */
public class Piece implements IPiece {
  private ArrayList<Beat> beats;
  private int tempo;

  /**
   * Creates a new empty piece of music with the given tempo.
   * @param tempo the tempo of the piece.
   */
  private Piece(int tempo) {
    this.beats = new ArrayList<Beat>();
    this.tempo = tempo;
  }

  @Override
  public void addNote(Pitch pitch, int octave, int start, int duration, int volume, int instrument)
          throws IllegalArgumentException {
    
    // ensure valid parameters
    if (start < 0) {
      throw new IllegalArgumentException("The start beat cannot be less than 0.");
    }

    if (duration < 1) {
      throw new IllegalArgumentException("The duration must be at least 1.");
    }

    // if the start beat is past the end of the current beats, add empty beats until the list of
    // beats is a proper size
    if ((duration == 1) && (start == this.beats.size())) {
      beats.add(new Beat(this.beats.size()));
    }
    else if (start + (duration - 1) >= this.beats.size()) {
      for (int i = this.beats.size(); i < start + (duration - 1); i++) {
        beats.add(new Beat(i));
      }
    }
    
    
    // now add the note
    beats.get(start).addNote(new Note(duration, start, octave, pitch, volume, instrument));
        
  }

  @Override
  public void removeNote(int startOf, Pitch pitch, int octave) throws IllegalArgumentException {
    if (startOf > this.beats.size() || startOf < 0) {
      throw new IllegalArgumentException("Invalid start beat.");
    }

    for (int i = 0; i < this.beats.get(startOf).getNotesAt().size(); i++) {
      Note note = this.beats.get(startOf).getNotesAt().get(i);
      if (note.getPitch() == pitch && note.getOctave() == octave) {
        this.beats.get(startOf).removeNoteAt(i);
        return;
      }
    }

    // if it makes it through the for loop without returning, throw exception
    throw new IllegalArgumentException("No such note exists.");
  }

  @Override
  public int getNumBeats() {
    return beats.size();
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public String toString() {
    if (beats.isEmpty()) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    ArrayList<ArrayList<String>> pieceGrid = new ArrayList<ArrayList<String>>();
    String startNote = "  X  ";
    String sustainNote = "  |  ";
    String noNote = "     ";

    // first column is padded to the number of digits the largest beat number is
    int firstColPad = Integer.toString(beats.get(beats.size() - 1).getBeatNum()).length();


    Note lowest = getLowestNote();

    // starts at lowest and gets incremented
    Note currNote = new Note(lowest);

    Note highest = getHighestNote();
    int noteRange = 1; // will be added to when making the first arraylist

    // first arraylist in piecegrid is just the range of notes
    ArrayList<String> firstRow = new ArrayList<String>();
    firstRow.add(String.format("%1$" + firstColPad + "s", ""));

    String pitchStr;

    while (currNote.compareTo(highest) <= 0) {
      pitchStr = currNote.toString();

      // ensure note representation is properly padded with spaces to 5 characters
      switch (pitchStr.length()) {
        case 2:
          pitchStr = "  " + pitchStr + " ";
          break;
        case 3:
          pitchStr = " " + pitchStr + " ";
          break;
        case 4:
          pitchStr = " " + pitchStr;
          break;
        default:
          // do nothing, it is the correct size already
      }

      // add to firstRow
      firstRow.add(pitchStr);

      // increment noterange for each iteration
      noteRange ++;

      // increment current
      currNote = currNote.getNextNote();
    }

    pieceGrid.add(firstRow);

    // add to pitchstr for all beats - one indexed because the first arraylist<String> is just the
    // range of pitches
    for (int i = 1; i <= beats.size(); i++) {
      pieceGrid.add(new ArrayList<String>());
      pieceGrid.get(i).add(String.format("%1$" + firstColPad + "s", Integer.toString(i - 1)));

      // add initially empty notes to all
      for (int j = 1; j < noteRange; j++) {
        pieceGrid.get(i).add(noNote);
      }
    }
    
    int notePosn = 0;

    // for each note in the song, replace the empty note with startNote at the starting position
    for (int i = 1; i <= this.beats.size(); i++) {
      for (Note n: this.beats.get(i - 1).getNotesAt()) {
        // sets notePosn to the position in the arraylist to place startNote
        notePosn = (n.getMidiVal() - lowest.getMidiVal()) + 1;
        pieceGrid.get(i).remove(notePosn);
        pieceGrid.get(i).add(notePosn, startNote);
        if (n.getDuration() > 1) {
          int beatsRemain = n.getDuration() - 1;
          for (int j = 1; j < beatsRemain; j++) {
            pieceGrid.get(i + j).remove(notePosn);
            pieceGrid.get(i + j).add(notePosn, sustainNote);
          }
        }
      }
    }


    // add complete 2d arraylist to string builder
    for (ArrayList<String> strList : pieceGrid) {
      for (String str : strList) {
        builder.append(str);
      }

      builder.append("\n");
    }

    builder.deleteCharAt(builder.length() - 1);

    return builder.toString();
  }

  @Override
  public Note getLowestNote() throws IllegalStateException {
    Note lowest = null;
    if (beats.size() == 0) {
      throw new IllegalStateException("Can only be used on a non-empty piece.");
    }

    int currBeat = 0;

    while (currBeat < beats.size() && lowest == null) {
      // if it's not an empty beat, make it the lowest.
      if (beats.get(currBeat).getNotesAt().size() > 0) {
        lowest = new Note(beats.get(currBeat).getNotesAt().get(0));
      }

      currBeat++;
    }

    if (lowest == null) {
      throw new IllegalStateException("Can only be used on a non-empty piece.");
    }

    Note currComparing = null;
    for (int i = currBeat; i < beats.size(); i++) {
      if (beats.get(i).getNotesAt().size() > 0) {
        currComparing = new Note(beats.get(i).getNotesAt().get(0));
        if (currComparing.compareTo(lowest) < 0) {
          lowest = beats.get(i).getNotesAt().get(0);
        }
      }
    }

    return new Note(lowest);
  }

  @Override
  public Note getHighestNote() throws IllegalStateException {
    Note highest = null;
    if (beats.size() == 0) {
      throw new IllegalStateException("Can only be used on a non-empty piece.");
    }

    int currBeat = 0;

    while (currBeat < beats.size() && highest == null) {
      // if it's not an empty beat, make it the lowest.
      if (beats.get(currBeat).getNotesAt().size() > 0) {
        highest = new Note(beats.get(currBeat).getNotesAt().get(beats.get(currBeat)
                .getNotesAt().size() - 1));
      }

      currBeat++;
    }

    if (highest == null) {
      throw new IllegalStateException("Can only be used on a non-empty piece.");
    }

    Note currComparing;
    for (int i = currBeat; i < beats.size(); i++) {
      if (beats.get(i).getNotesAt().size() > 0) {
        currComparing = new Note(beats.get(i).getNotesAt().get(beats.get(i)
                .getNotesAt().size() - 1));
        if (currComparing.compareTo(highest) > 0) {
          highest = beats.get(i).getNotesAt().get(beats.get(i).getNotesAt().size() - 1);
        }
      }
    }

    return new Note(highest);
  }

  @Override
  public int getNoteRange() {
    if (beats.isEmpty()) {
      return 0;
    }

    int noteRange = 1;

    Note lowest = getLowestNote();
    Note highest = getHighestNote();

    while (lowest.compareTo(highest) <= 0) {
      // increment noterange for each iteration
      noteRange ++;

      // increment current
      lowest = lowest.getNextNote();
    }

    return noteRange;
  }
  
  @Override
  public List<Beat> getBeats() {
    ArrayList<Beat> tempList = new ArrayList<Beat>();
    
    for (Beat b: this.beats) {
      tempList.add(b);
      //System.out.print(numBeat + "Beats \n");
      //numBeat++;
    }
    
    return tempList;
  }

  @Override
  public void incrementTempo(int increment) {
    // only decrement if it will not make the tempo negative
    if (tempo - increment > 0) {
      tempo -= increment;
    }
  }

  @Override
  public void decrementTempo(int decrement) {
    // only decrement if it will not make the tempo negative
    tempo += decrement;
  }

  public static final class Builder implements CompositionBuilder<IPiece> {
    private ArrayList<Note> notes = new ArrayList<Note>();
    private int tempo = 10000; // an arbitrary default value for tempo
    
    
    public Builder() {
      this.notes = new ArrayList<Note>();
    }
    
    
    @Override
    public IPiece build() {
      IPiece piece = new Piece(tempo);

      for (Note note : notes) {
        piece.addNote(note.getPitch(), note.getOctave(), note.getFirstBeatOf(), note.getDuration(),
                note.getVolume(), note.getInstrument());
      }

      return piece;
    }

    @Override
    public CompositionBuilder<IPiece> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<IPiece> addNote(int start, int end, int instrument, int pitch,
                                              int volume) {
      int octave = (int) Math.floor(pitch / 12);

      Pitch ptch = Pitch.getPitchFromVal(pitch % 12);

      notes.add(new Note(end - start + 1, start, octave, ptch, volume, instrument));

      return this;
    }
  }
}
