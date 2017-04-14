package cs3500.music.model;

import java.util.Objects;

/**
 * A note has duration that it plays for, the octave it plays in, the pitch it is played at,
 * and the number of the first beat it plays at. It can be compared to other notes in terms of
 * the octave and pitch that it is at - notes at higher pitches and octaves are greater than notes
 * at lower pitches. compareTo is blind to how far along in the piece a note is played at, as it
 * is only used to sort notes within a beat.
 */
public class Note implements Comparable<Note> {
  private final int duration;
  private final int octave;
  private final Pitch pitch;
  private final int firstBeatOf;
  private final int volume;
  private final int instrument;

  /**
   * Constructs a note from individual parameters.
   * @param duration the duration the note plays for.
   * @param firstBeatOf the first beat of the note.
   * @param octave the octave of the note.
   * @param pitch the pitch of the note.
   */
  public Note(int duration, int firstBeatOf, int octave, Pitch pitch, int volume, int instrument) {
    if (duration < 1) {
      throw new IllegalArgumentException("Duration must be at least one.");
    }

    if (firstBeatOf < 0) {
      throw new IllegalArgumentException("Invalid beat number.");
    }

    this.duration = duration;
    this.octave = octave;
    this.firstBeatOf = firstBeatOf;
    this.pitch = pitch;
    this.volume = volume;
    this.instrument = instrument;
  }

  /**
   * A convenience constructor that constructs a copy of a note from an already existing note.
   * @param note the note to copy
   */
  public Note(Note note) {
    this.duration = note.getDuration();
    this.firstBeatOf = note.getFirstBeatOf();
    this.octave = note.getOctave();
    this.pitch = note.getPitch();
    this.volume = note.getVolume();
    this.instrument = note.getInstrument();
  }

  @Override
  public int compareTo(Note note) {
    // NOTE: Unlike Object.equals() (also overriden by this class), compareTo() only cares about
    // the octave and pitch of the compared notes, as it is ordering them in terms of pitch.

    // wil throw a nullpointerexception if the given note is null
    Objects.requireNonNull(note);

    // CHANGED HW06: the added getmidival gave a more elegant way to do the compareTo method
    return this.getMidiVal() - note.getMidiVal();
  }

  /**
   * Gets the note's duration.
   * @return the duration.
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets the octave that the note is in.
   * @return the octave
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Gets the pitch that the note is.
   * @return the pitch
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Gets the beat at which the note starts.
   * @return the beat number
   */
  public int getFirstBeatOf() {
    return this.firstBeatOf;
  }

  public int getVolume() {
    return this.volume;
  }

  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Retrieves the next highest note in terms of pitch and octave.
   * @return the next highest note (duration and beat don't matter)
   */
  public Note getNextNote() {
    switch (pitch) {
      case C:
        return new Note(duration, firstBeatOf, octave, Pitch.CSHARP, volume, instrument);
      case CSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.D, volume, instrument);
      case D:
        return new Note(duration, firstBeatOf, octave, Pitch.DSHARP, volume, instrument);
      case DSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.E, volume, instrument);
      case E:
        return new Note(duration, firstBeatOf, octave, Pitch.F, volume, instrument);
      case F:
        return new Note(duration, firstBeatOf, octave, Pitch.FSHARP, volume, instrument);
      case FSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.G, volume, instrument);
      case G:
        return new Note(duration, firstBeatOf, octave, Pitch.GSHARP, volume, instrument);
      case GSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.A, volume, instrument);
      case A:
        return new Note(duration, firstBeatOf, octave, Pitch.ASHARP, volume, instrument);
      case ASHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.B, volume, instrument);
      case B:
        // c but in the next octave
        return new Note(duration, firstBeatOf, octave + 1, Pitch.C, volume, instrument);
      default:
        // will never happen
        return null;
    }
  }

  /**
   * ADDED HW06: Gets the next lowest note after this note.
   * @return the previous note.
   */
  public Note getPreviousNote() {
    switch (pitch) {
      case C:
        return new Note(duration, firstBeatOf, octave - 1, Pitch.B, volume, instrument);
      case CSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.C, volume, instrument);
      case D:
        return new Note(duration, firstBeatOf, octave, Pitch.CSHARP, volume, instrument);
      case DSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.D, volume, instrument);
      case E:
        return new Note(duration, firstBeatOf, octave, Pitch.DSHARP, volume, instrument);
      case F:
        return new Note(duration, firstBeatOf, octave, Pitch.E, volume, instrument);
      case FSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.F, volume, instrument);
      case G:
        return new Note(duration, firstBeatOf, octave, Pitch.FSHARP, volume, instrument);
      case GSHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.G, volume, instrument);
      case A:
        return new Note(duration, firstBeatOf, octave, Pitch.GSHARP, volume, instrument);
      case ASHARP:
        return new Note(duration, firstBeatOf, octave, Pitch.A, volume, instrument);
      case B:
        return new Note(duration, firstBeatOf, octave, Pitch.ASHARP, volume, instrument);
      default:
        // will never happen
        return null;
    }
  }

  @Override
  public String toString() {
    return this.pitch.toString() + this.octave;
  }

  @Override
  public boolean equals(Object o) {
    if (! (o instanceof Note)) {
      return false;
    }

    Note that = (Note) o;

    return this.duration == that.duration && this.firstBeatOf == that.firstBeatOf
            && this.pitch == that.pitch && this.octave == that.octave;
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, firstBeatOf, pitch, octave);
  }

  /**
   * ADDED HW06: Retrieves an int that represents the pitch and octave in a way that midi
   * understands. Example: C5 = 60.
   * @return the midi note number.
   */
  public int getMidiVal() {
    return 12 * this.octave + pitch.getValue();
  }
}
