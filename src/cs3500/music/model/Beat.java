package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A representation of a beat of music. Each beat has a number so it can be ordered in the piece,
 * and a list of notes that BEGIN at that beat. A note may not be added to the beat if it does not
 * begin at that beat, even if that beat is contained inside its duration.
 */
public class Beat {
  private ArrayList<Note> notesAt;
  private final int beatNum;

  /**
   * Constructs a new beat with an initially empty ArrayList of notes.
   * @param beatNum the number this beat is, zero indexed
   * @throws IllegalArgumentException if beatNum is less than 1.
   */
  Beat(int beatNum) throws IllegalArgumentException {
    if (beatNum < 0) {
      throw new IllegalArgumentException("Beat numbers must be at least 0.");
    }

    this.beatNum = beatNum;
    this.notesAt = new ArrayList<Note>();
  }

  /**
   * Adds a new note to the beat.
   * @param note the note to add
   * @throws IllegalArgumentException if the note does not start at this beat
   */
  void addNote(Note note) throws IllegalArgumentException {
    if (this.beatNum != note.getFirstBeatOf()) {
      throw new IllegalArgumentException("You must add a note to the beat it begins at.");
    }

    this.notesAt.add(note);
    Collections.sort(notesAt);
  }

  /**
   * Retrieves the beat number of this beat.
   * @return the beat number, one indexed
   */
  int getBeatNum() {
    return this.beatNum;
  }

  /**
   * Gets an arraylist of all the notes that start at this beat.
   * @return a list of all the notes.
   */
  public ArrayList<Note> getNotesAt() {
    // changed for HW 6 to return a copy of the list instead of the list itself.
    ArrayList<Note> copyList = new ArrayList<Note>();

    for (Note n: this.notesAt) {
      copyList.add(new Note(n));
    }

    return copyList;
  }

  /**
   * Removes the note at the given index in the list.
   * @param at the note to be removed.
   * @throws IndexOutOfBoundsException if at is not a valid in bounds parameter.
   */
  void removeNoteAt(int at) throws IndexOutOfBoundsException {
    if (at >= this.notesAt.size() || at < 0) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }

    this.notesAt.remove(at);
  }
}
