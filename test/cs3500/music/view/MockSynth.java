package cs3500.music.view;

import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.VoiceStatus;
import javax.sound.midi.Instrument;
import javax.sound.midi.Patch;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import java.util.List;

/**
 * A mock Midi Synthesizer for testing purposes.
 */
public class MockSynth implements Synthesizer {
  private boolean isOpen = false;
  private StringBuffer sb;

  public MockSynth(StringBuffer sb) {
    this.sb = sb;
  }

  @Override
  public int getMaxPolyphony() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public long getLatency() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public MidiChannel[] getChannels() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public VoiceStatus[] getVoiceStatus() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public boolean loadInstrument(Instrument instrument) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public void unloadInstrument(Instrument instrument) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Soundbank getDefaultSoundbank() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Instrument[] getAvailableInstruments() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Instrument[] getLoadedInstruments() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public void unloadAllInstruments(Soundbank soundbank) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Info getDeviceInfo() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public void open() throws MidiUnavailableException {
    this.isOpen = true;
  }

  @Override
  public void close() {
    this.isOpen = false;
  }

  @Override
  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public long getMicrosecondPosition() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public int getMaxReceivers() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public int getMaxTransmitters() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return new MockReciever(this.sb);
  }

  @Override
  public List<Receiver> getReceivers() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }

  @Override
  public List<Transmitter> getTransmitters() {
    throw new UnsupportedOperationException("Unsupported in mock.");
  }
}
