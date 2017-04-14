package cs3500.music.model;

import cs3500.music.provider.model.*;
import cs3500.music.provider.model.Beat;

import java.util.HashMap;

/**
 * An adapter of our music model to our provider's music model interface.
 */
public class ProviderModel implements cs3500.music.provider.model.MusicModel {
  private IPiece piece;

  public ProviderModel(IPiece piece) {
    this.piece = piece;
  }

  @Override
  public String getMusicState() {
    return piece.toString();
  }

  @Override
  public MusicModel combine(MusicModel given) throws IllegalArgumentException {
    // todo
    return null;
  }

  @Override
  public void addNote(int start, int end, int instrument, int pitch, int volume)
          throws IllegalArgumentException {
    Pitch p = getPitchFromProvider(pitch);
    int octave = getOctaveFromProvider(pitch);

    piece.addNote(p, octave, start, end - start - 1, volume, instrument);
  }

  @Override
  public void remove(int pitch, int atWhich) throws IllegalArgumentException {
    piece.removeNote(atWhich, getPitchFromProvider(pitch), getOctaveFromProvider(pitch));
  }

  @Override
  public MusicModel addMusic(MusicModel given) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String getPitchName(int pitch) {
    Pitch p = getPitchFromProvider(pitch);
    int octave = getOctaveFromProvider(pitch);

    return p.toString() + octave;
  }

  @Override
  public HashMap<Integer, HashMap<Integer, Beat>> map() {
    // TODO: 4/14/2017 do this 
    return null;
  }

  @Override
  public int number() {
    // TODO: 4/14/2017 is this correct 
    return piece.getNoteRange();
  }

  @Override
  public int getTempo() {
    return piece.getTempo();
  }

  /**
   * A helper for returning the correct pitch enum from the int pur providers use to represent
   * both pitch and octave.
   * @param providerPitch the provider's pitch
   * @return our pitch
   */
  private Pitch getPitchFromProvider(int providerPitch) {
    // TODO: 4/14/2017 allign with providers 
    return Pitch.getPitchFromVal(providerPitch % 12);
  }

  /**
   * A helper for returning the correct octave from the int pur providers use to represent
   * both pitch and octave.
   * @param providerPitch the provider's pitch
   * @return our pitch
   */
  private int getOctaveFromProvider(int providerPitch) {
    // TODO: 4/14/2017 allign with providers 
    return (int) Math.floor(providerPitch % 12);
  }
}
