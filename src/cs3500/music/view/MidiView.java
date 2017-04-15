package cs3500.music.view;

import cs3500.music.model.Beat;
import cs3500.music.model.IPiece;
import cs3500.music.model.Note;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * The midi audio view for the piece.
 */
public class MidiView implements IView {
  private Synthesizer synth;
  private Receiver receiver;
  private IPiece viewModel;
  private int currBeat;
  private boolean paused;

  /**
   * A convenience constructor for testing purposes.
   * @param synthesizer the mock synthesizer.
   */
  public MidiView(Synthesizer synthesizer) {
    try {
      this.synth = synthesizer;
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.currBeat = 0;
      this.paused = false;
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates and opens the synthesizer, and creates a receiver from the synthesizer.
   */
  public MidiView() {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.currBeat = 0;
      this.paused = false;
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Plays all of the notes in the piece.
   */
  private void playNotes() throws InvalidMidiDataException {
    playNotesAt();
  }

  @Override
  public void assignPiece(IPiece piece) {
    this.viewModel = piece;
  }

  @Override
  public void show(int beat) {
    this.currBeat = beat;
    try {
      playNotes();
    } catch (InvalidMidiDataException e) {
      System.out.println("Midi error.");
    }
  }

  @Override
  public void scrollForward() {
    if (currBeat <= (viewModel.getNumBeats()) - 1) {
      currBeat++;
    }
  }

  @Override
  public void scrollBack() {
    if (currBeat > 0) {
      currBeat--;
    }
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    //does nothing
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    // does nothing
  }

  @Override
  public void togglePause() {
    paused = !paused;
  }

  @Override
  public Note getNoteOnPiano() {
    return null;
  }

  @Override
  public void update() {
    //does nothing
  }

  /**
   * Creates a starting message for the midi note.
   * @param instrument the instrument (channel) of the note
   * @param pitch the pitch, in midi form, the note plays at
   * @param volume the volume that the note plays at.
   * @return the start message.
   * @throws InvalidMidiDataException if the given information is invalid.
   */
  private MidiMessage getStartNote(int instrument, int pitch, int volume)
          throws InvalidMidiDataException {
    return new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
  }

  /**
   * Creates a starting message for the midi note.
   * @param instrument the instrument (channel) of the note
   * @param pitch the pitch, in midi form, the note plays at
   * @param volume the volume that the note plays at.
   * @return the start message.
   * @throws InvalidMidiDataException if the given information is invalid.
   */
  private MidiMessage getEndNote(int instrument, int pitch, int volume)
          throws InvalidMidiDataException {
    return new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
  }

  /**
   * Gets the microsecond timing of the start of the given beat.
   * @param beat the beat
   * @return the midi timestamp
   */
  private static int getMidiTimestamp(int beat, int tempo) {
    return tempo * beat;
  }

  /**
   * Plays all of the notes starting at the current beat.
   * @throws InvalidMidiDataException in the case of a Midi error.
   */
  private void playNotesAt() throws InvalidMidiDataException {
    int tempo = viewModel.getTempo();
    Beat current = viewModel.getBeats().get(currBeat);

    if (!paused) {
      for (Note note : current.getNotesAt()) {
        this.receiver.send(getStartNote(note.getInstrument(), note.getMidiVal(), note.getVolume()),
                getMidiTimestamp(note.getFirstBeatOf(),tempo));
        this.receiver.send(getEndNote(note.getInstrument(), note.getMidiVal(), note.getVolume()),
                getMidiTimestamp(note.getFirstBeatOf() + note.getDuration(), tempo));
      }
    }
  }
}
