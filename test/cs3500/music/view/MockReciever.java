package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * A mock midi receiver for testing purposes.
 */
class MockReciever implements Receiver {
  private boolean isOpen = true;
  private StringBuffer sb;

  public MockReciever(StringBuffer sb) {
    this.sb = sb;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    // Typecast to ShortMessage as this is what our view uses
    ShortMessage sMessage = (ShortMessage) message;

    // if open, send to  the stringbuilder.
    if (isOpen) {
      if (sMessage.getCommand() == ShortMessage.NOTE_ON) {
        sb.append("Note on message sent at " + timeStamp + "\n");
      }
      else {
        sb.append("Note off message sent at " + timeStamp + "\n");
      }
    }
  }

  @Override
  public void close() {
    isOpen = false;
  }
}
