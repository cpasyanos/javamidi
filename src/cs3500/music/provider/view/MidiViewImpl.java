package cs3500.music.provider.view;

import cs3500.music.provider.model.Beat;

import javax.sound.midi.*;
import java.util.HashMap;

/**
 * A skeleton for MIDI playback.
 * a Hashmap of the distribution of Beats.
 * a tempo , the speed of this music.
 * a voulme, the beat length of this music.
 */
public class MidiViewImpl implements IView {
    private final HashMap<Integer, HashMap<Integer, Beat>> amp;
    private final int tempo;
    private final int volume;
    private Sequencer sqcer;


    /**
     * the constructor.
     *
     * @param amp    a hashmap.
     * @param tempo  an int.
     * @param volume an int.
     * @param sqcer  a Sequencer.
     */
    public MidiViewImpl(HashMap<Integer, HashMap<Integer, Beat>> amp, int tempo, int volume,
                        Sequencer sqcer) {
        this.amp = amp;
        this.tempo = tempo;
        this.volume = volume;
        this.sqcer = sqcer;
    }

    /**
     * Relevant classes and methods from the javax.sound.midi library:
     * <ul>
     * <li>{@link MidiSystem#getSynthesizer()}</li>
     * <li>{@link Synthesizer}
     * <ul>
     * <li>{@link Synthesizer#open()}</li>
     * <li>{@link Synthesizer#getReceiver()}</li>
     * <li>{@link Synthesizer#getChannels()}</li>
     * </ul>
     * </li>
     * <li>{@link Receiver}
     * <ul>
     * <li>{@link Receiver#send(MidiMessage, long)}</li>
     * <li>{@link Receiver#close()}</li>
     * </ul>
     * </li>
     * <li>{@link MidiMessage}</li>
     * <li>{@link ShortMessage}</li>
     * <li>{@link MidiChannel}
     * <ul>
     * <li>{@link MidiChannel#getProgram()}</li>
     * <li>{@link MidiChannel#programChange(int)}</li>
     * </ul>
     * </li>
     * </ul>
     *
     * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
     * https://en.wikipedia.org/wiki/General_MIDI
     * </a>
     */

    public void initialize() throws InvalidMidiDataException, MidiUnavailableException {

        Sequence se = new Sequence(Sequence.PPQ, 1);
        Track trc = se.createTrack();


        sqcer.setTempoInMPQ(tempo);

        int start = 0;
        int end = 0;

        for (int j = 0; j < 128; j++) {
            if (!amp.get(j).isEmpty()) {
                if (start == 0) {
                    start = j;
                }
                end = j;
            }
        }

        for (int k = start; k <= end; k++) {
            for (int m = 0; m <= volume; m++) {
                Beat tem = amp.get(k).get(m);
                if (tem != null && tem.type == 1) {

                    MidiMessage star = new ShortMessage(ShortMessage.NOTE_ON, tem.instrument - 1,
                            tem.pitch, tem.volume);
                    MidiMessage sto = new ShortMessage(ShortMessage.NOTE_OFF, tem.instrument - 1,
                            tem.pitch, tem.volume);
                    MidiEvent sta = new MidiEvent(star, m);
                    MidiEvent so = new MidiEvent(sto, m + tem.duration);
                    trc.add(sta);
                    trc.add(so);

                }
            }

        }
        sqcer.open();
        sqcer.setSequence(se);
        sqcer.start();
        sqcer.setTempoInMPQ(tempo);


    }

}
