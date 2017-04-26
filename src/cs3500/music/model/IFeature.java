package cs3500.music.model;

/**
 * Created by Jade Benner on 26-Apr-17.
 */
public interface IFeature extends Comparable<IFeature> {
  /**
   *
   * @return an integer representing the beat the feature is located in.
   */
  int getLocation();
  
  
  /**
   *
   * @return whether the feature is a repeat.
   */
  boolean isRepeat();
}
