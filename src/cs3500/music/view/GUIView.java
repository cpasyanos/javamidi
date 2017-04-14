package cs3500.music.view;

import cs3500.music.controller.KeyHandler;
import cs3500.music.model.Beat;
import cs3500.music.model.Note;
import cs3500.music.model.ViewModel;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JViewport;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The GUIView implementation. CHANGED HW07: Made window larger, made top part of graphic larger
 * in order to fit the pieces with a wider range of notes.
 */
public class GUIView implements IGUIView {
  private ViewModel piece;
  private MusicFrame frame;
  private JScrollPane scoreHolder;
  private ScoreGraphic score;
  private PianoGraphic piano;
  private JPanel scoreWrapper;
  private int currBeat;
  private int rightEdge;
  private int leftEdge;
  private boolean paused;
  private int init;

  /**
   * The constructor for the gui view. CurrBeat is set to 0 because a song always starts at 0.
   * Piece is set to null until assignPiece is null.
   */
  public GUIView() {
    this.piece = null;
    this.frame = new MusicFrame();
    this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.scoreHolder = null;
    this.score = null;
    this.piano = null;
    this.scoreWrapper = new JPanel();

    this.leftEdge = 0;
    this.rightEdge = 44;

    this.currBeat = 0;
    this.paused = true;
    
    this.init = 0;


  }
  
  
  @Override
  public void assignPiece(ViewModel piece) {
    this.piece = piece;
    this.frame.setPiece(piece);
    this.piano = new PianoGraphic(this.piece, this.getList());
  }
  
  @Override
  public void scrollForward() {
    score.scrollForward();
    piano.scrollForward();

    if (currBeat <= (piece.getNumBeats()) - 1) {
      currBeat++;
    }
    if (currBeat == rightEdge) {
      this.scrollViewForward();
    }
    frame.repaint(0,0,0,1200,1200);
    //System.out.println("++");
    resetFocus();
  }
  
  @Override
  public void scrollBack() {
    score.scrollBack();
    piano.scrollBack();
    if (currBeat > 0) {
      currBeat--;
    }
    if (currBeat == leftEdge) {
      this.scrollViewBack();
    }
    frame.repaint(0,0,0,1200,1200);
    //System.out.println("--");
    resetFocus();
  }
  
  @Override
  public void show(int beat) {
    if (init != 1) {
      init = 1;
      this.currBeat = beat;
      this.score = new ScoreGraphic(this.piece, this.getList());

      this.score.setCurrBeat(this.currBeat);
      this.piano.setCurrBeat(this.currBeat);
  
      this.scoreWrapper = new JPanel();
      scoreWrapper.add(score);
      scoreHolder = new JScrollPane(scoreWrapper);
  
      this.addKeyListener(new KeyHandler());
  
      score.setFocusable(false);
  
      JPanel displayPanel = new JPanel();
      displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
      displayPanel.setPreferredSize(new Dimension(1200, 1000));
  
      JPanel top = new JPanel();
      top.setLayout(new BorderLayout());
      top.setPreferredSize(new Dimension(1200, 600));
  
      JPanel bottom = new JPanel();
      bottom.setLayout(new BorderLayout());
      bottom.setPreferredSize(new Dimension(1200, 400));
  
      NoteGraphic notes = new NoteGraphic(this.piece);
  
      bottom.add(piano, BorderLayout.CENTER);
  
      notes.setPreferredSize(new Dimension(100, 600));
      notes.setFocusable(false);
      top.add(notes, BorderLayout.WEST);
  
      score.setPreferredSize(new Dimension(25 * piece.getNumBeats() + 200, 600));
  
      scoreHolder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scoreHolder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
  
      scoreHolder.setPreferredSize(new Dimension(1100, 600));
      top.add(scoreHolder);
  
      bottom.setFocusable(false);
  
      displayPanel.add(top, BorderLayout.NORTH);
      displayPanel.add(bottom, BorderLayout.SOUTH);
  
      this.frame.setResizable(false);
      this.frame.setContentPane(displayPanel);
      this.frame.pack();
      this.frame.setVisible(true);
      //scoreHolder.setFocusable(false);
  
      paused = false;
  
      resetFocus();
    }
    
    else {
      frame.repaint(0, 0, 0, 1200, 1200);
      resetFocus();
    }
  }

  @Override
  public void resetFocus() {
    this.frame.setFocusable(true);
    this.frame.requestFocus();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    frame.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    piano.addMouseListener(listener);
  }

  @Override
  public void togglePause() {
    paused = !paused;
  }

  @Override
  public Note getNoteOnPiano() {
    return piano.getClickedNote();
  }

  @Override
  public void update() {
    piano.invalidate();

    this.piano.updateList(this.getList());
    this.score.updateList(this.getList());
    init = 0;

    // piano.invalidate();

    frame.repaint(0,0,0,1200,1200);
  }

  /**
   * Creates a list of every note in the piece allowing for more efficient iteration.
   * @return the list of notes.
   */
  private List<Note> getList() {
    ArrayList<Note> notes = new ArrayList<Note>();
    for (Beat b: this.piece.getBeats()) {
      for (Note n: b.getNotesAt()) {
        notes.add(n);
      }
    }
    return notes;
  }

  /**
   * Scrolls the view forward.
   */
  private void scrollViewForward() {
    if (currBeat >= 44) {
      Point tempPoint = scoreHolder.getViewport().getViewPosition();
      Point newPoint = new Point();
      newPoint.setLocation(tempPoint.getX() + 100, tempPoint.getY());
      JViewport view = scoreHolder.getViewport();
      view.setViewPosition(newPoint);
      scoreHolder.setViewport(view);
      this.rightEdge += 4;
      this.leftEdge += 4;
    }
  }

  /**
   * Scrolls the view back.
   */
  private void scrollViewBack() {
    if (currBeat > 0) {
      Point tempPoint = scoreHolder.getViewport().getViewPosition();
      Point newPoint = new Point();
      newPoint.setLocation(tempPoint.getX() - 100, tempPoint.getY());
      JViewport view = scoreHolder.getViewport();
      view.setViewPosition(newPoint);
      scoreHolder.setViewport(view);
      this.rightEdge -= 4;
      this.leftEdge -= 4;
    }
  }
}