package cs3500.music.util;

import cs3500.music.view.IView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GUIView;
import cs3500.music.view.MidiView;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ProviderConsoleView;
import cs3500.music.view.ProviderCompositeView;
import cs3500.music.view.ProviderGUIView;
import cs3500.music.view.ProviderMidiView;

/**
 * A factory class that will return the relevant view when given a string corresponding to said
 * view.
 */
public class OurViewFactory {

  /**
   * Returns a view of the given type. "console" will return a text console view, "gui" will return
   * a popup gui view, "midi" will play the piece with audio, and "composite" will return a midi
   * view synchronized with the gui view.
   *
   * @param s the type of view to return
   * @return the view
   */
  public static IView makeView(String s) {
    switch (s.toLowerCase()) {
      case "console":
        return new ConsoleView();
      case "gui":
        return new GUIView();
      case "midi":
        return new MidiView();

      // ADDED HW07: plays a midi view while diplaying a GUI
      case "composite":
        return new CompositeView();

      // ADDED HW08: Options for our provider views
      case "provider console":
        return new ProviderConsoleView();
      case "provider gui":
        return new ProviderGUIView();
      case "provider midi":
        return new ProviderMidiView();
      case "provider composite":
        return new ProviderCompositeView();

      default:
        throw new IllegalArgumentException("Invalid View Type.");
    }
  }
}
