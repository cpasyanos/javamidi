package cs3500.music.model;

import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.model.Beat;

import java.util.HashMap;
import java.util.List;

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
  public HashMap<Integer, HashMap<Integer, cs3500.music.provider.model.Beat>> map() {
    // TODO: 4/14/2017 do this
    HashMap<Integer, HashMap<Integer, cs3500.music.provider.model.Beat>> tempMap =
            new HashMap<Integer, HashMap<Integer, cs3500.music.provider.model.Beat>>(512);
    List<cs3500.music.model.Beat> beatList = piece.getBeats();

    for (int i = 0; i < piece.getNumBeats(); i++) {
      tempMap.put(i, new HashMap<Integer, cs3500.music.provider.model.Beat>(256));

      /*for (int j = 0; j < 128; j++) {
        tempMap.get(i).put(j, null);
      }*/

      for(Note n: beatList.get(i).getNotesAt()) {
        int mapInt = 0;
        tempMap.get(i).put(mapInt, this.noteToProviderBeat(n));
        mapInt++;
      }
    }

    return tempMap;
  }

  @Override
  public int number() {
    // TODO: 4/14/2017 is this correct 
    return 127;
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
    return Pitch.getPitchFromVal((providerPitch - 1) % 12);
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
