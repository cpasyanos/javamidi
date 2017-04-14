package cs3500.music.provider.model;

import java.util.HashMap;

/**
 * Created by 28609 on 3/1/2017.
 * a music model normally represent a piece of music. Having a hashmap to represent;
 * the distribution of beats. An int --tempo, to represent the speed to play this piece of music.
 * An int -- volume, to represent the beat length of a piece of music.
 */
public interface MusicModel {


    /**
     * print the current music state as a well-organized String.
     */
    String getMusicState();

    /**
     * combine the given music to current music to produce a new piece of music.
     *
     * @param given a volume of music.
     * @throws IllegalArgumentException if the two music cannot be combined.
     */
    MusicModel combine(MusicModel given) throws
            IllegalArgumentException;

    /**
     * Adds a new note to the piece.
     *
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a piano)
     * @param volume     The volume (in the range [0, 127])
     */
    void addNote(int start, int end, int instrument, int pitch, int volume)
            throws IllegalArgumentException;

    /**
     * if there is a note at the given volume in the given pitch, then remove it.
     *
     * @param pitch   given the serialNumber of a pitch.
     * @param atWhich given the number of a certain volume.
     * @throws IllegalArgumentException if pitch or atWhich beyond 15 or this music's largest.
     */
    void remove(int pitch, int atWhich) throws IllegalArgumentException;


    /**
     * add the given music at the end of this current music.
     *
     * @param given a certain volume of music.
     * @throws IllegalArgumentException if the given music cannot be added to this music.
     */
    MusicModel addMusic(MusicModel given) throws
            IllegalArgumentException;


    /**
     * input a pitch number, return a pitch String name. Example, 0 -> "C1", 10-> "A#1"
     *
     * @param pitch the int.
     * @return a String like "C1", "C#1" or "A#1"
     */
    String getPitchName(int pitch);

    /**
     * a method to get the hashmap of this model.
     *
     * @return a hashmap.
     */
    HashMap<Integer, HashMap<Integer, Beat>> map();

    /**
     * a method to get the volume of this model.
     *
     * @return the volume.
     */
    int number();

    /**
     * a method to get the tempo(the speed to play this piece of music) of this model
     *
     * @return an int.
     */
    int getTempo();
}
