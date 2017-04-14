package cs3500.music.provider.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Created by 28609 on 3/15/2017. this is the IView interface. contain a method -- initialize();
 * it will have different implementations according to users: "console", "visual" and "midi"
 */
public interface IView {

    /**
     * the method to build a view according to IView implementations
     *
     * @throws InvalidMidiDataException if there is invalid Midi data.
     * @throws MidiUnavailableException if Midi is unavailable.
     */
    void initialize() throws InvalidMidiDataException, MidiUnavailableException;
//  /**
//   * Displays/draws/plays the song.
//   * @param notes the notes of the song.
//   */
//  void draw(List<Note> notes);
//
//
//  /**
//   * Finish displaying the song.
//   * @param c the controller calling the view's methods.
//   */
//  void finish(MusicController c);
}
