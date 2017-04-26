package cs3500.music.controller;

import cs3500.music.model.IPiece;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.ViewModel;
import cs3500.music.util.MusicReader;
import cs3500.music.util.OurViewFactory;
import cs3500.music.view.IView;

import java.awt.event.KeyEvent;

import java.io.FileReader;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The implementation of the controller interface.
 */
public class Controller implements IController {
  private IView view;
  private IPiece model;
  private int currBeat;
  private boolean paused;
  private Date time;
  private boolean mousePressed;
  private int beatStart;

  // the amount to increment or decrement the tempo by
  private static int deltaTempo = 5000;
  
  
  /**
   * Constructs a model and view based on the file and view type indicated in the arguments.
   * It also configures the mouse and keyboard listeners so the user may add notes and scroll
   * through the piece.
   *
   * @param filename the name of the file to be read.
   * @param viewType the type of view to use.
   * @throws IOException if the file is invalid.
   */
  public Controller(String filename, String viewType) throws IOException {
    FileReader file = new FileReader(filename);

    this.model = MusicReader.parseFile(file, new Piece.Builder());

    ViewModel viewModel = new ViewModel();
    viewModel.assignPiece(this.model);

    this.view = OurViewFactory.makeView(viewType);

    this.view.assignPiece(viewModel);

    configureKeyBoardListener();
    configureMouseListener();

    paused = true;
    currBeat = 0;
  }

  @Override
  public void play() {
    Objects.requireNonNull(model, "Model was not properly assigned.");
    Objects.requireNonNull(view, "View was not properly assigned.");

    paused = false;

    view.show(currBeat);
    
    Play playObject = new Play();
    while (currBeat <= (model.getNumBeats() + 1)) {
      while (!paused) {
        if (currBeat <= model.getNumBeats() - 2) {
          view.show(currBeat);
        }
        try {
          Thread.sleep(model.getTempo() / 1000);
        } catch (InterruptedException e) {
          System.out.print("Thread interrupted.");
        }

        playObject.run();

        if (currBeat == model.getNumBeats()) {
          paused = true;
        }
      }
      if (mousePressed) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          System.out.print("Thread interrupted.");
        }
        if (mousePressed) {
          new BeatDuration().run();
        }
      }
      // this makes it able to play again after pausing (????)
      System.out.print("");
    }
  }

  /**
   * A helper class for configuring key input by adding all supported key commands.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<Integer,Runnable>();
    
    keyPresses.put(KeyEvent.VK_RIGHT, new ScrollForward());
    keyPresses.put(KeyEvent.VK_LEFT, new ScrollBack());
    keyPresses.put(KeyEvent.VK_SPACE, new TogglePause());
    keyPresses.put(KeyEvent.VK_END, new ToEnd());
    keyPresses.put(KeyEvent.VK_HOME, new ToStart());
    keyPresses.put(KeyEvent.VK_UP, new IncrementTempo());
    keyPresses.put(KeyEvent.VK_DOWN, new DecrementTempo());

    KeyHandler handler = new KeyHandler();

    handler.setKeyPressedMap(keyPresses);

    view.addKeyListener(handler);
  }

  /**
   * A helper class for configuring mouse input by adding all supported mouse operations.
   */
  private void configureMouseListener() {
    Map<Integer, Runnable> runnableMap = new HashMap<Integer, Runnable>();
  
    runnableMap.put(0, new StartAddNotes());
    runnableMap.put(1, new EndAddNotes());

    MouseHandler handler = new MouseHandler();
    handler.setRunnableMap(runnableMap);

    view.addMouseListener(handler);
  }

  /**
   * A functional class allowing the right arrow key to scroll the view forward.
   */
  private class ScrollForward implements Runnable {

    @Override
    public void run() {
      if (paused) {
        view.scrollForward();

        if (currBeat < model.getNumBeats()) {
          currBeat++;
        }
      }
    }
  }

  /**
   * A functional class allowing the left arrow key to scroll the view back.
   */
  private class ScrollBack implements Runnable {

    @Override
    public void run() {
      if (paused) {
        view.scrollBack();

        if (currBeat > 0) {
          currBeat--;
        }
      }
    }
  }

  /**
   * A functional class allowing the spacebar to toggle the pause state of the view.
   */
  private class TogglePause implements Runnable {

    @Override
    public void run() {
      paused = !paused;
    }
  }

  /**
   * A functional class that scrolls the view forward when it is not paused.
   */
  private class Play implements Runnable {
    
    @Override
    public void run() {
  
      if (currBeat < model.getNumBeats()) {
        view.scrollForward();
        currBeat++;
      }
    }
  }

  /**
   * A functional class allowing the End button to scroll to the end of the piece.
   */
  private class ToEnd implements Runnable {
  
    @Override
    public void run() {
      if (paused) {
        while (currBeat < model.getNumBeats()) {
          view.scrollForward();
          currBeat++;
        }
        currBeat = model.getNumBeats();
      }
    }
  }

  /**
   * A functional class allowing the Home button to scroll to the beginning of the piece.
   */
  private class ToStart implements Runnable {
    
    @Override
    public void run() {
      if (paused) {
        while (currBeat > 0) {
          view.scrollBack();
          currBeat--;
        }
        currBeat = 0;
      }
    }
  }
  
  /**
   * A functional class allowing a mouse click on the piano graphic to add a new note.
   */
  private class StartAddNotes implements Runnable {
    
    @Override
    public void run() {
      if (paused) {
        mousePressed = true;
        time = new Date();
        beatStart = currBeat;
      }
    }
  }
  /**
   * A functional class allowing a mouse click on the piano graphic to add a new note.
   */
  private class EndAddNotes implements Runnable {
    
    @Override
    public void run() {
      if (paused) {
        mousePressed = false;
        long timePressed = new Date().getTime() - time.getTime();
        System.out.println(timePressed);
        Note toAdd = view.getNoteOnPiano();
        int noteDur;
        if (timePressed < 500) {
          noteDur = 1;
          System.out.println(noteDur);
          view.scrollForward();
          if (currBeat < model.getNumBeats()) {
            currBeat++;
          }
        }
        else {
          noteDur = (int) (timePressed / 500);
          System.out.println(noteDur);
        }
        
        model.addNote(toAdd.getPitch(), toAdd.getOctave(), beatStart, noteDur+1, 70, 0);
        
        // update view to reflect new note
        view.update();
        // scroll to next beat after adding note
        view.show(currBeat);
      }
    }
  }

  /**
   * ADDED HW09: A functional class allowing the up arrow key to increment the tempo.
   */
  private class IncrementTempo implements Runnable {

    @Override
    public void run() {
      if (paused) {
        model.incrementTempo(deltaTempo);
      }
    }
  }

  /**
   * ADDED HW09: A functional class allowing the down arrow key to decrement the tempo.
   */
  private class DecrementTempo implements Runnable {

    @Override
    public void run() {
      if (paused) {
        model.decrementTempo(deltaTempo);
      }
    }
  }
  
  /**
   * TODO:
   */
  private class BeatDuration implements Runnable {
    
    @Override
    public void run() {
      if (paused) {
        view.scrollForward();
        if (currBeat < model.getNumBeats()) {
          currBeat++;
        }
        view.show(currBeat);
        
      }
    }
  }
  
}
