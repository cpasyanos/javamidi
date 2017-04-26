package cs3500.music.model;

/**
 * Created by Jade Benner on 26-Apr-17.
 */
public class Repeat implements IFeature {
  int location;
  
  @Override
  public int getLocation() {
    return location;
  }
  
  @Override
  public boolean isRepeat() {
    return true;
  }
  
  @Override
  public int compareTo(IFeature o) {
    if(o.isRepeat()) {
      return 0;
    }
    else {
      return 1000;
    }
  }
}
