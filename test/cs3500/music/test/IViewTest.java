package cs3500.music.test;

import cs3500.music.model.IPiece;
import cs3500.music.model.Piece;
import cs3500.music.model.ViewModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.MidiView;

import cs3500.music.view.MockConsoleView;
import cs3500.music.view.MockSynth;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

/**
 * A test for the midi view.
 */
public class IViewTest {

  /**
   * A test for midi playback in the midi view.
   * @throws FileNotFoundException If the file given does not exist.
   */
  @Test
  public void testPlayback() throws FileNotFoundException {
    FileReader file = new FileReader("mary-little-lamb.txt");
    ViewModel viewModel = new ViewModel();
    CompositionBuilder<IPiece> builder = new Piece.Builder();
    IPiece piece = MusicReader.parseFile(file, builder);
    viewModel.assignPiece(piece);

    StringBuffer out = new StringBuffer();
    IView mockMidiView = new MidiView(new MockSynth(out));

    mockMidiView.assignPiece(viewModel);

    for (int i = 0; i < piece.getNumBeats(); i++) {
      mockMidiView.show(i);
    }

    assertEquals("Note on message sent at 0\n"
            + "Note off message sent at 1600000\n"
            + "Note on message sent at 0\n"
            + "Note off message sent at 600000\n"
            + "Note on message sent at 400000\n"
            + "Note off message sent at 1000000\n"
            + "Note on message sent at 800000\n"
            + "Note off message sent at 1400000\n"
            + "Note on message sent at 1200000\n"
            + "Note off message sent at 1800000\n"
            + "Note on message sent at 1600000\n"
            + "Note off message sent at 3200000\n"
            + "Note on message sent at 1600000\n"
            + "Note off message sent at 2200000\n"
            + "Note on message sent at 2000000\n"
            + "Note off message sent at 2600000\n"
            + "Note on message sent at 2400000\n"
            + "Note off message sent at 3200000\n"
            + "Note on message sent at 3200000\n"
            + "Note off message sent at 5000000\n"
            + "Note on message sent at 3200000\n"
            + "Note off message sent at 3800000\n"
            + "Note on message sent at 3600000\n"
            + "Note off message sent at 4200000\n"
            + "Note on message sent at 4000000\n"
            + "Note off message sent at 5000000\n"
            + "Note on message sent at 4800000\n"
            + "Note off message sent at 5400000\n"
            + "Note on message sent at 4800000\n"
            + "Note off message sent at 5400000\n"
            + "Note on message sent at 5200000\n"
            + "Note off message sent at 5800000\n"
            + "Note on message sent at 5600000\n"
            + "Note off message sent at 6600000\n"
            + "Note on message sent at 6400000\n"
            + "Note off message sent at 8200000\n"
            + "Note on message sent at 6400000\n"
            + "Note off message sent at 7000000\n"
            + "Note on message sent at 6800000\n"
            + "Note off message sent at 7400000\n"
            + "Note on message sent at 7200000\n"
            + "Note off message sent at 7800000\n"
            + "Note on message sent at 7600000\n"
            + "Note off message sent at 8200000\n"
            + "Note on message sent at 8000000\n"
            + "Note off message sent at 9800000\n"
            + "Note on message sent at 8000000\n"
            + "Note off message sent at 8600000\n"
            + "Note on message sent at 8400000\n"
            + "Note off message sent at 9000000\n"
            + "Note on message sent at 8800000\n"
            + "Note off message sent at 9400000\n"
            + "Note on message sent at 9200000\n"
            + "Note off message sent at 9800000\n"
            + "Note on message sent at 9600000\n"
            + "Note off message sent at 11400000\n"
            + "Note on message sent at 9600000\n"
            + "Note off message sent at 10200000\n"
            + "Note on message sent at 10000000\n"
            + "Note off message sent at 10600000\n"
            + "Note on message sent at 10400000\n"
            + "Note off message sent at 11000000\n"
            + "Note on message sent at 10800000\n"
            + "Note off message sent at 11400000\n"
            + "Note on message sent at 11200000\n"
            + "Note off message sent at 13000000\n"
            + "Note on message sent at 11200000\n"
            + "Note off message sent at 13000000\n", out.toString());
  }

  @Test
  public void testConsole() throws FileNotFoundException {
    FileReader file = new FileReader("mary-little-lamb.txt");
    ViewModel viewModel = new ViewModel();
    CompositionBuilder<IPiece> builder = new Piece.Builder();
    IPiece piece = MusicReader.parseFile(file, builder);
    viewModel.assignPiece(piece);

    StringBuffer out = new StringBuffer();
    IView mockConsoleView = new MockConsoleView(out, viewModel);

    mockConsoleView.show(0);

    assertEquals("    E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   " +
                    "E5   F5  F#5   G5 \n" +
            " 0                 X                                            X                 " +
                    "\n" +
            " 1                 |                                            |                 " +
                    "\n" +
            " 2                 |                                  X                           " +
                    "\n" +
            " 3                 |                                  |                           " +
                    "\n" +
            " 4                 |                        X                                     " +
                    "\n" +
            " 5                 |                        |                                     " +
                    "\n" +
            " 6                 |                                  X                           " +
                    "\n" +
            " 7                                                    |                           " +
                    "\n" +
            " 8                 X                                            X                 " +
                    "\n" +
            " 9                 |                                            |                 " +
                    "\n" +
            "10                 |                                            X                 " +
                    "\n" +
            "11                 |                                            |                 " +
                    "\n" +
            "12                 |                                            X                 " +
                    "\n" +
            "13                 |                                            |                 " +
                    "\n" +
            "14                 |                                            |                 " +
                    "\n" +
            "15                                                                                " +
                    "\n" +
            "16                 X                                  X                           " +
                    "\n" +
            "17                 |                                  |                           " +
                    "\n" +
            "18                 |                                  X                           " +
                    "\n" +
            "19                 |                                  |                           " +
                    "\n" +
            "20                 |                                  X                           " +
                    "\n" +
            "21                 |                                  |                           " +
                    "\n" +
            "22                 |                                  |                           " +
                    "\n" +
            "23                 |                                  |                           " +
                    "\n" +
            "24                 X                                            X                 " +
                    "\n" +
            "25                 |                                            |                 " +
                    "\n" +
            "26                                                                             X  " +
                    "\n" +
            "27                                                                             |  " +
                    "\n" +
            "28                                                                             X  " +
                    "\n" +
            "29                                                                             |  " +
                    "\n" +
            "30                                                                             |  " +
                    "\n" +
            "31                                                                             |  " +
                    "\n" +
            "32                 X                                            X                 " +
                    "\n" +
            "33                 |                                            |                 " +
                    "\n" +
            "34                 |                                  X                           " +
                    "\n" +
            "35                 |                                  |                           " +
                    "\n" +
            "36                 |                        X                                     " +
                    "\n" +
            "37                 |                        |                                     " +
                    "\n" +
            "38                 |                                  X                           " +
                    "\n" +
            "39                 |                                  |                           " +
                    "\n" +
            "40                 X                                            X                 " +
                    "\n" +
            "41                 |                                            |                 " +
                    "\n" +
            "42                 |                                            X                 " +
                    "\n" +
            "43                 |                                            |                 " +
                    "\n" +
            "44                 |                                            X                 " +
                    "\n" +
            "45                 |                                            |                 " +
                    "\n" +
            "46                 |                                            X                 " +
                    "\n" +
            "47                 |                                            |                 " +
                    "\n" +
            "48                 X                                  X                           " +
                    "\n" +
            "49                 |                                  |                           " +
                    "\n" +
            "50                 |                                  X                           " +
                    "\n" +
            "51                 |                                  |                           " +
                    "\n" +
            "52                 |                                            X                 " +
                    "\n" +
            "53                 |                                            |                 " +
                    "\n" +
            "54                 |                                  X                           " +
                    "\n" +
            "55                 |                                  |                           " +
                    "\n" +
            "56  X                                       X                                     " +
                    "\n" +
            "57  |                                       |                                     " +
                    "\n" +
            "58  |                                       |                                     " +
                    "\n" +
            "59  |                                       |                                     " +
                    "\n" +
            "60  |                                       |                                     " +
                    "\n" +
            "61  |                                       |                                     " +
                    "\n" +
            "62  |                                       |                                     " +
                    "\n" +
            "63  |                                       |                                     ",
            out.toString());
  }
}
