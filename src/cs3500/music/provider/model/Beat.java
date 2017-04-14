package cs3500.music.provider.model;

/**
 * Created by 28609 on 3/3/2017.
 * a class to represent a beat. Having 4 fields: int -- instrument to represent.
 * the instrument to play this beat.
 * int--pitch to represent the pitch to play this beat.
 * int--volume to explain how loud this beat should be played.
 * int-- type to represent it's a head(1) of a note or a sustain(0) of a note.
 * int duration. if it's head beat, this represent this note's duration.if not , intput 0;
 */
public class Beat {

    public final int instrument;
    public final int pitch;
    public final int volume;
    public final int type;
    public int duration;

    /**
     * the constructor of a beat.
     *
     * @param instrument a number to represent an instrument.
     * @param pitch      a number to represent a pitch.
     * @param volume     a number to prepresent a volume. range [0-127]
     * @param type       one of 1 or 0 to present it's a "head" or a "sustain"
     */
    public Beat(int instrument, int pitch, int volume, int type, int duration) {

        this.instrument = instrument;
        this.pitch = pitch;
        this.volume = volume;
        this.type = type;
        this.duration = duration;
    }

}
