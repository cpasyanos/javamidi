package cs3500.music.model;

/**
 * An enumeration that encompasses all 12 pitches within an octave. Each pitch has a value
 * so that they can be compared mathematically, whether or not it is a sharp, and a string
 * representation.
 * CHANGE HW06: Removed boolean isSharp value, as it was unnecessary.
 */
public enum Pitch {
  C(0, "C"), CSHARP(1, "C#"), D(2, "D"), DSHARP(3, "D#"), E(4, "E"), F(5, "F"), FSHARP(6, "F#"),
  G(7, "G"), GSHARP(8, "G#"), A(9, "A"), ASHARP(10, "A#"), B(11, "B");

  private final int value;
  private final String stringVal;

  /**
   * A constructor for a pitch (only used internally).
   * @param value the value of the note
   * @param stringVal the string value of the note
   */
  Pitch(int value, String stringVal) {
    this.value = value;
    this.stringVal = stringVal;
  }

  /**
   * Returns the pitch's int value.
   * @return the value
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Retrieves the pitch given an int value.
   * @param val the value.
   * @return the correct pitch.
   * @throws IllegalArgumentException if the value is negative.
   */
  public static Pitch getPitchFromVal(int val) throws IllegalArgumentException {
    switch (val) {
      case 0:
        return C;
      case 1:
        return CSHARP;
      case 2:
        return D;
      case 3:
        return DSHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return FSHARP;
      case 7:
        return G;
      case 8:
        return GSHARP;
      case 9:
        return A;
      case 10:
        return ASHARP;
      case 11:
        return B;
      default:
        if (val < 0) {
          throw new IllegalArgumentException("Val cannot be negative.");
        }
        // iterate with modulo of original value
        return getPitchFromVal(val % 11);
    }
  }

  @Override
  public String toString() {
    return this.stringVal;
  }
}
