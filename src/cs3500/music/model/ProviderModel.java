package cs3500.music.model;

import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.model.Beat;

import java.util.HashMap;

/**
 * An adapter of our music model to our provider's music model interface.
 */
public class ProviderModel implements cs3500.music.provider.model.MusicModel {
  private IPiece piece;
  private int volume; //I dont entirely understand what this is.

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
  public HashMap<Integer, HashMap<Integer, cs3500.music.provider.model.Beat>> map() {
    HashMap<Integer, HashMap<Integer, cs3500.music.provider.model.Beat>> result = new HashMap<>();
    for (int i = 0; i < 128; i++) {
      result.put(i, new HashMap<Integer, Beat>());
    }
    for (int i = 0; i < piece.getNumBeats(); i++) {

      for (Note n : piece.getBeats().get(i).getNotesAt()) {

        if (n.getFirstBeatOf() + n.getDuration() > this.volume) {
          this.volume = n.getFirstBeatOf() + n.getDuration();
        }

        HashMap<Integer, Beat> tem = result.get(n.getMidiVal());
        tem.put(n.getFirstBeatOf(),
                new Beat(n.getInstrument(), n.getMidiVal(), volume, 1, n.getDuration()));
        for (int j = n.getFirstBeatOf() + 1; i <= n.getFirstBeatOf() + n.getDuration(); i++) {
          tem.put(j, this.noteToProviderBeat(n));
        }
      }
    }

    return result;
  }

  @Override
  public int number() {
    return this.volume;
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
    // TODO: 4/14/2017 align with providers
    return Pitch.getPitchFromVal((providerPitch - 1) % 12);
  }

  /**
   * A helper for returning the correct octave from the int pur providers use to represent
   * both pitch and octave.
   * @param providerPitch the provider's pitch
   * @return our pitch
   */
  private int getOctaveFromProvider(int providerPitch) {
    // TODO: 4/14/2017 align with providers
    return (int) Math.floor(providerPitch % 12);
  }

  /**
   * Converts our Note into the Provider's equivalent object.
   * @param note the note to be converted.
   * @return the Provider's equivalent notion.
   */
  private cs3500.music.provider.model.Beat noteToProviderBeat(Note note) {
    cs3500.music.provider.model.Beat tempBeat =
            new cs3500.music.provider.model.Beat(note.getInstrument() + 1,
                    note.getMidiVal(), note.getVolume(), 1, note.getDuration());

    return tempBeat;
  }
}
