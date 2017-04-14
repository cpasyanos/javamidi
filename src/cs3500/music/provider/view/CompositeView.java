package cs3500.music.provider.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;

import cs3500.music.provider.model.Beat;
import cs3500.music.provider.model.MusicModel;

/**
 * Created by 28609 on 4/2/2017.
 */
public class CompositeView extends JFrame implements IView {
  private MusicModel model;
  private Sequencer sqcer;
  private JScrollPane js;
  private DrawingAndSound jc;


  public CompositeView(MusicModel model, Sequencer sqcer) {
    this.model = model;
    this.sqcer = sqcer;
    js = null;
    jc = null;

  }

  @Override
  public void initialize() throws InvalidMidiDataException, MidiUnavailableException {

    Sequence se = new Sequence(Sequence.PPQ, 1);
    Track trc = se.createTrack();
    sqcer.setTempoInMPQ(model.getTempo());

    int start = 0;
    int end = 0;

    for (int j = 0; j < 128; j++) {
      if (!model.map().get(j).isEmpty()) {
        if (start == 0) {
          start = j;
        }
        end = j;
      }
    }

    for (int k = start; k <= end; k++) {
      for (int m = 0; m <= model.number(); m++) {
        Beat tem = model.map().get(k).get(m);
        if (tem != null && tem.type == 1) {
          MidiMessage star = new ShortMessage(ShortMessage.NOTE_ON,
                  tem.instrument - 1, tem.pitch, tem.volume);
          MidiMessage sto = new ShortMessage(ShortMessage.NOTE_OFF,
                  tem.instrument - 1, tem.pitch, tem.volume);
          MidiEvent sta = new MidiEvent(star, m);
          MidiEvent so = new MidiEvent(sto, m + tem.duration - 1);
          trc.add(sta);
          trc.add(so);

        }
      }

    }
    sqcer.open();
    sqcer.setSequence(se);


    jc = new DrawingAndSound(model, sqcer);
    jc.setBackground(new Color(255, 255, 255));
    setBackground(Color.lightGray);
    DrawingKeyboard jk = new DrawingKeyboard(model, sqcer);
    jk.setSize(1500, 300);

    js = new JScrollPane(jc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    js.addKeyListener(new KeyListen());
    js.setSize(1500, 700);

    setTitle("composite view");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    setSize(new Dimension(1520, 1000));

    GridLayout experimentLayout = new GridLayout(2, 1);
    setLayout(experimentLayout);

    add(js);
    add(jk);
    jk.addMouseListener(new MouseListen());
    jk.setVisible(true);
    setLocationRelativeTo(null);

    setFocusable(true);
    requestFocus();
    js.requestFocusInWindow();

    sqcer.start();
    sqcer.setTempoInMPQ(model.getTempo());
    while (true) {
      int temp = (int) sqcer.getTickPosition();
      int tem = temp * 20;
      int scro = js.getHorizontalScrollBar().getValue();
      int width = (int)js.getSize().getWidth();
      if (tem > scro + width - 100 || tem < scro) {
        js.getHorizontalScrollBar().setValue(tem);
        js.repaint();
      }
    }
  }


  private class KeyListen implements KeyListener {

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
      /*nothing to do*/
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
      /*nothing to do*/
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_S) {
        if (sqcer.isRunning()) {
          sqcer.stop();

        } else {
          sqcer.start();
          sqcer.setTempoInMPQ(model.getTempo());

        }
      } else if (e.getKeyCode() == KeyEvent.VK_HOME) {
        js.getHorizontalScrollBar().setValue(0);
      } else if (e.getKeyCode() == KeyEvent.VK_END) {
        int ma = js.getHorizontalScrollBar().getMaximum();
        js.getHorizontalScrollBar().setValue(ma);
      } else {
        /*do nothing*/
      }
    }
  }

  private class MouseListen implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      int tem = 0;
      if (x >= 50 && x <= 1450 && y >= 80 && y <= 230) {
        int octave = (x - 50) / 140;
        int rest = (x - 50) % 140;
        if (y <= 140) {
          if (14 < rest && rest < 26) {
            tem = octave * 12 + 1;
          } else if (34 < rest && rest < 46) {
            tem = octave * 12 + 3;
          } else if (74 < rest && rest < 86) {
            tem = octave * 12 + 6;
          } else if (94 < rest && rest < 106) {
            tem = octave * 12 + 8;
          } else if (114 < rest && rest < 126) {
            tem = octave * 12 + 10;
          } else if (0 < rest && rest < 14) {
            tem = octave * 12;
          } else if (26 < rest && rest < 34) {
            tem = octave * 12 + 2;
          } else if (46 < rest && rest < 60) {
            tem = octave * 12 + 4;
          } else if (60 < rest && rest < 74) {
            tem = octave * 12 + 5;
          } else if (86 < rest && rest < 94) {
            tem = octave * 12 + 7;
          } else if (106 < rest && rest < 114) {
            tem = octave * 12 + 9;
          } else {
            tem = octave * 12 + 11;
          }
        } else if (140 < y && y <= 230) {
          int g = rest / 20;
          if (g == 0) {
            tem = octave * 12;
          } else if (g == 1) {
            tem = octave * 12 + 2;
          } else if (g == 2) {
            tem = octave * 12 + 4;
          } else if (g == 3) {
            tem = octave * 12 + 5;
          } else if (g == 4) {
            tem = octave * 12 + 7;
          } else if (g == 5) {
            tem = octave * 12 + 9;
          } else if (g == 6) {
            tem = octave * 12 + 11;
          } else {
            /*do nothing*/
          }
        }

        model.addNote((int) sqcer.getTickPosition(), (int) sqcer.getTickPosition(),
                1, tem, 85);


      } else {
        /*do nothing*/
      }

      jc.repaint();


    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
  }
}
