package cs3500.music.provider.view;

import cs3500.music.provider.model.Beat;
import cs3500.music.provider.model.MusicModel;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Created by 28609 on 3/29/2017.
 */
public class TextualView implements IView {
    private final MusicModel mu;

    public TextualView(MusicModel mu) {
        this.mu = mu;
    }

    @Override
    public void initialize() throws InvalidMidiDataException, MidiUnavailableException {
        int start = 0;
        int end = 0;
        for (int j = 0; j < 128; j++) {

            if (!mu.map().get(j).isEmpty()) {
                if (start == 0) {
                    start = j;
                }
                end = j;
            }
        }
        String total = "    ";
        for (int k = start; k <= end; k++) {
            String now = String.format("%0$-5s", mu.getPitchName(k));
            total = total + now;
        }


        for (int i = 0; i < mu.number(); i++) {
            String volumeIndex = "\n" + String.format("%03d", i) + " ";
            for (int j = start; j <= end; j++) {
                Beat tem = mu.map().get(j).get(i);
                if (tem != null) {
                    if (tem.type == 1) {
                        volumeIndex = volumeIndex + "X    ";
                    } else {
                        volumeIndex = volumeIndex + "|    ";
                    }
                } else {
                    volumeIndex = volumeIndex + "     ";
                }
            }
            total = total + volumeIndex;
        }

        System.out.print(total);

    }
}
