package cs3500.music.provider.model;

import cs3500.music.util.CompositionBuilder;

import java.util.HashMap;

/**
 * Created by 28609 on 3/2/2017.
 * this MusicModel1 is an implementation of MusicModel.
 * has 3 fields: a hashmap to represent the distribution of beats. a volume to the represent the.
 * the beat length of this piece of music.
 * a tempo to represent the speed of how this piece of music should be played.
 */


public class MusicModel1 implements MusicModel {
    private HashMap<Integer, HashMap<Integer, Beat>> pieceOfMusic;
    private int volume;
    private int tempo;

    /**
     * the default constructor for this model1.
     *
     * @param pieceOfMusic a hashmap to represent 16 pitches.
     * @param volume       the number of beat thhis piece of music will take.
     * @param tempo        the speed how this music is played.
     */
    MusicModel1(HashMap<Integer, HashMap<Integer, Beat>> pieceOfMusic, int volume, int tempo) {
        this.pieceOfMusic = pieceOfMusic;
        this.volume = volume;
        this.tempo = tempo;

    }

    /**
     * a convenient constructor to make an instance of MusicModel1 in order.
     * to call methods in this class.
     */
    public MusicModel1() {
    /*nothing to fill*/
    }

    /**
     * A constructor with a Builder.
     *
     * @param b represents the Builder.
     */
    private MusicModel1(Builder b) {
        this.pieceOfMusic = b.pieceOfMusic;
        this.volume = b.volume;
        this.tempo = b.tempo;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, Beat>> map() {
        return this.pieceOfMusic;
    }

    @Override
    public int number() {
        return this.volume;
    }

    @Override
    public int getTempo() {
        return tempo;
    }


    @Override
    public String getMusicState() {
        int start = 0;
        int end = 0;
        for (int j = 0; j < 128; j++) {

            if (!this.pieceOfMusic.get(j).isEmpty()) {
                if (start == 0) {
                    start = j;
                }
                end = j;
            }
        }
        String total = "    ";
        for (int k = start; k <= end; k++) {
            String now = String.format("%0$-5s", getPitchName(k));
            total = total + now;
        }


        for (int i = 0; i < volume; i++) {
            String volumeIndex = "\n" + String.format("%03d", i) + " ";
            for (int j = start; j <= end; j++) {
                Beat tem = this.pieceOfMusic.get(j).get(i);
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

        return total;

    }

    @Override
    public MusicModel combine(MusicModel given) throws
            IllegalArgumentException {
        HashMap<Integer, HashMap<Integer, Beat>> wanted = new HashMap<>();
        if (given instanceof MusicModel1) {
            int givenVolume = ((MusicModel1) given).volume;
            int volumeSet = Math.max(this.volume, givenVolume);
            for (int j = 0; j < 128; j++) {
                HashMap<Integer, Beat> tem = new HashMap<>();
                for (int i = 0; i < volumeSet; i++) {
                    Beat fromThis = this.pieceOfMusic.get(j).get(i);
                    Beat fromGiven = ((MusicModel1) given).pieceOfMusic.get(j).get(i);
                    if (fromGiven == null && fromThis != null) {
                        tem.put(i, fromThis);
                    } else if (fromGiven != null && fromThis == null) {
                        tem.put(i, fromGiven);
                    } else if (fromGiven != null && fromThis != null) {
                        tem.put(i, fromThis);
                    } else {
            /*do nothing*/
                    }
                }
                wanted.put(j, tem);
            }
            return new MusicModel1(wanted, givenVolume, 100);
        } else {
            throw new IllegalArgumentException("given music is not the same type, cannot combine them!");
        }
    }

    @Override
    public void addNote(int start, int end, int instrument, int pitch, int volume)
            throws IllegalArgumentException {
        if (start > end) {
            throw new IllegalArgumentException("not right input!");
        }
        if (end > this.volume) {
            this.volume = end;
        }
        HashMap<Integer, Beat> tem = this.pieceOfMusic.get(pitch);
        tem.put(start, new Beat(instrument, pitch, volume, 1, end - start + 1));
        if (start < end) {
            for (int i = start + 1; i <= end; i++) {
                tem.put(i, new Beat(instrument, pitch, volume, 0, 0));
            }
        }

    }

    @Override
    public void remove(int pitch, int atWhich) throws IllegalArgumentException {
        if (pitch > 127 || atWhich >= this.volume) {
            new IllegalArgumentException("invalid input");
        } else {
            this.pieceOfMusic.get(pitch).remove(atWhich);
            int i = atWhich + 1;
            while (this.pieceOfMusic.get(pitch).get(i) != null) {
                this.pieceOfMusic.get(pitch).remove(i);
                i++;
            }
        }
    }


    @Override
    public String getPitchName(int pitch) {
        int suffix = pitch / 12 + 1;
        int prefix = (pitch + 1) % 12;
        String name = "";
        switch (prefix) {
            case 1:
                name = "C" + suffix;
                break;
            case 2:
                name = "C#" + suffix;
                break;
            case 3:
                name = "D" + suffix;
                break;
            case 4:
                name = "D#" + suffix;
                break;
            case 5:
                name = "E" + suffix;
                break;
            case 6:
                name = "F" + suffix;
                break;
            case 7:
                name = "F#" + suffix;
                break;
            case 8:
                name = "G" + suffix;
                break;
            case 9:
                name = "G#" + suffix;
                break;
            case 10:
                name = "A" + suffix;
                break;
            case 11:
                name = "A#" + suffix;
                break;
            case 0:
                name = "B" + suffix;
                break;
            default:
        /*  */
        }
        return name;
    }

    @Override
    public MusicModel addMusic(MusicModel given) throws IllegalArgumentException {
        if (given instanceof MusicModel1) {
            int givenVolume = ((MusicModel1) given).volume;
            int newVolume = this.volume + givenVolume;
            HashMap<Integer, HashMap<Integer, Beat>> wanted = new HashMap<>();
            for (int j = 0; j < 128; j++) {
                HashMap<Integer, Beat> presentThis = this.pieceOfMusic.get(j);
                HashMap<Integer, Beat> tem = new HashMap<>();
                if (presentThis != null) {
                    for (int i = 0; i < this.volume; i++) {
                        Beat now = this.pieceOfMusic.get(j).get(i);
                        if (now != null) {
                            tem.put(i, now);
                        }
                    }
                }
                HashMap<Integer, Beat> presentGiven = ((MusicModel1) given).pieceOfMusic.get(j);
                if (presentGiven != null) {
                    for (int k = 0; k < givenVolume; k++) {
                        Beat now1 = ((MusicModel1) given).pieceOfMusic.get(j).get(k);
                        if (now1 != null) {
                            tem.put(k + this.volume, now1);
                        }
                    }
                }
                wanted.put(j, tem);
            }
            return new MusicModel1(wanted, newVolume, 100);
        } else {
            throw new IllegalArgumentException("not the same type of music, cannot add to it!");
        }

    }

    /**
     * a Builder used to construct a MusicModel1. it has the same fields as MusicModel1.
     */
    public static final class Builder implements CompositionBuilder<MusicModel> {
        private HashMap<Integer, HashMap<Integer, Beat>> pieceOfMusic;
        private int volume;
        private int tempo;

        /**
         * to fill the pieceofMusic field
         *
         * @param p the hashmap to represent a piecofMusic.
         * @return a builder.
         */
        public Builder hash(HashMap<Integer, HashMap<Integer, Beat>> p) {

            this.pieceOfMusic = p;
            return this;
        }

        /**
         * to fill the volume field.
         *
         * @param v the input int.
         * @return this builder.
         */
        public Builder volume(int v) {
            this.volume = v;
            return this;
        }

        /**
         * to fill the tempo field.
         *
         * @param t the input int.
         * @return this builder.
         */
        public Builder tempo(int t) {
            this.tempo = t;
            return this;
        }

        @Override
        public MusicModel1 build() {

            return new MusicModel1(this);
        }

        @Override
        public CompositionBuilder<MusicModel> setTempo(int tempo) {
            this.tempo = tempo;
            return this;


        }

        @Override
        public CompositionBuilder<MusicModel> addNote(int start, int end, int instrument,
                                                      int pitch, int volume) {
            if (end > this.volume) {
                this.volume = end;
            }
            HashMap<Integer, Beat> tem = this.pieceOfMusic.get(pitch);
            tem.put(start, new Beat(instrument, pitch, volume, 1, end - start + 1));
            for (int i = start + 1; i <= end; i++) {
                tem.put(i, new Beat(instrument, pitch, volume, 0, 0));
            }

            return this;
        }

    }
}
