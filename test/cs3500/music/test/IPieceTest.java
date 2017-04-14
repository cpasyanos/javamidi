package cs3500.music.test;

/*
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.music.model.IPiece;
*/

/**
 * Tests for the model and its related classes.
 */
public class IPieceTest {


  /*
  HW06 CHANGE: Commented out these tests, as they depended on the previous implementation of the
  model. There is no way to make an IPiece outside of the builder, for example. Other pieces of
  the model that should stay inside the model were refactored to lower privacy as well.

  @Test
  public void testNote() {
    Note note1 = new Note(4, 6, 5, Pitch.C);

    // test getters
    assertEquals(4, note1.getDuration());
    assertEquals(5, note1.getOctave());
    assertEquals(6, note1.getFirstBeatOf());
    assertEquals(Pitch.C, note1.getPitch());

    // test compareTo
    Note note2 = new Note(4, 6, 3, Pitch.D);
    assertTrue(note1.compareTo(note2) > 0);
    assertTrue(note2.compareTo(note1) < 0);

    Note note3 = new Note(1, 6, 5, Pitch.D);
    assertTrue(note1.compareTo(note3) < 0);

    Note note4 = new Note(1, 1, 5, Pitch.C);
    assertEquals(0, note4.compareTo(note1));
  }

  @Test (expected = NullPointerException.class)
  public void testNullCompareNote() {
    Note note1 = new Note(4, 6, 5, Pitch.C);
    note1.compareTo(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructorParam() {
    Note note1 = new Note(-4, 6, 5, Pitch.C);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadConstructorParam2() {
    Note note1 = new Note(4, -3, 5, Pitch.C);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadBeatNum() {
    Beat bad = new Beat(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMismatchedNote() {
    // a note at the wrong start beat will throw an error
    Note badNote = new Note(4, 1, 6, Pitch.ASHARP);
    Beat beat = new Beat(2);
    beat.addNote(badNote);
  }

  @Test
  public void testAddRemoveNote() {
    Beat beat = new Beat(2);
    assertEquals(0, beat.getNotesAt().size());

    beat.addNote(new Note(1, 2, 5, Pitch.ASHARP));
    beat.addNote(new Note(6, 2, 5, Pitch.FSHARP));

    assertEquals(2, beat.getNotesAt().size());

    // remove the second added note
    beat.removeNoteAt(1);
    assertEquals(1, beat.getNotesAt().size());
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testBadRemove() {
    Beat beat = new Beat(2);
    assertEquals(0, beat.getNotesAt().size());

    beat.addNote(new Note(1, 2, 5, Pitch.ASHARP));
    beat.addNote(new Note(6, 2, 5, Pitch.FSHARP));

    beat.removeNoteAt(2);
  }

  @Test
  public void testAddingNotes() {
    // a newly created piece should be empty
    IPiece piece = new Piece();

    assertEquals(0, piece.getNumBeats());

    // add a two-beat duration note at beat #3, and there should be 5 beats in the piece
    // (0, 1, 2, 3, and 4 - note starts at 3 and goes to 4)
    piece.addNote(Pitch.A, 3, 3, 2);

    assertEquals(5, piece.getNumBeats());

    // adding another note that is within the range of beats already should not change the size
    piece.addNote(Pitch.CSHARP, 5, 0, 3);

    assertEquals(5, piece.getNumBeats());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadNoteRemove() {
    IPiece piece = new Piece();

    piece.addNote(Pitch.DSHARP, 5, 1, 2);
    // try to remove a note at an out of bounds beat.

    piece.removeNote(10, Pitch.F, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadNoteRemove2() {
    IPiece piece = new Piece();

    piece.addNote(Pitch.E, 5, 6, 1);

    piece.removeNote(6, Pitch.GSHARP, 4);
  }

  @Test
  public void testConsoleOutput() {
    IPiece piece = new Piece();
    piece.addNote(Pitch.E, 5, 6, 1);

    assertEquals("   E5 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3     \n"
            + "4     \n"
            + "5     \n"
            + "6  X  ", piece.toString());

    piece.addNote(Pitch.E, 5, 0, 4);

    assertEquals("   E5 \n"
            + "0  X  \n"
            + "1  |  \n"
            + "2  |  \n"
            + "3  |  \n"
            + "4     \n"
            + "5     \n"
            + "6  X  ", piece.toString());

    piece.addNote(Pitch.B, 5, 2, 6);

    assertEquals("   E5   F5  F#5   G5  G#5   A5  A#5   B5 \n"
            + "0  X                                     \n"
            + "1  |                                     \n"
            + "2  |                                  X  \n"
            + "3  |                                  |  \n"
            + "4                                     |  \n"
            + "5                                     |  \n"
            + "6  X                                  |  \n"
            + "7                                     |  ", piece.toString());
  }
  */
}