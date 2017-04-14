package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.IController;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  /**
   * The main music editor method.
   *
   * @param args Two strings, one the name of the file, and two, the type of view output wanted.
   * @throws IOException in the case of a file reading error
   * @throws InvalidMidiDataException in the case of a midi error
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    IController controller = new Controller(args[0], args[1]);

    controller.play();
  }
}
