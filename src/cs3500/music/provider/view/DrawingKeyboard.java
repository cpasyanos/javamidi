package cs3500.music.provider.view;

import cs3500.music.provider.model.Beat;
import cs3500.music.provider.model.MusicModel;

import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;

/**
 * Created by 28609 on 4/4/2017.
 */
class DrawingKeyboard extends JPanel {
    private int currentLine;
    private MusicModel mo;
    private Sequencer sq;

    /**
     * the constructor
     *
     * @param mo the model.
     * @param sq the sqc
     */
    public DrawingKeyboard(MusicModel mo, Sequencer sq) {
        super();
        this.mo = mo;
        this.currentLine = 0;
        this.sq = sq;
        setBackground(new Color(204, 204, 204));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        setPreferredSize(new Dimension(1500, 200));

        HashMap<Integer, HashMap<Integer, Beat>> mp = mo.map();
        int numOfBeats = mo.number();

        int start = 0;
        int end = 0;

        for (int j = 0; j < 128; j++) {


            if (!mp.get(j).isEmpty()) {
                if (start == 0) {
                    start = j;
                }
                end = j;
            }
        }
        int total = end - start + 1;

        currentLine = (int) sq.getTickPosition();

        g2.setColor(new Color(255, 255, 255));
        for (int i = 0; i < 70; i++) {
            Rectangle tem = new Rectangle(50 + i * 20,
                    80, 20, 150);

            g2.fill(tem);

        }

        g2.setColor(Color.YELLOW);
        for (int i = start; i <= end; i++) {
            Beat tem = mp.get(i).get(currentLine);
            if (tem != null) {
                int letter = i % 12;
                int octave = i / 12;
                if (letter == 0 || letter == 2 || letter == 4) {
                    Rectangle temp = new Rectangle(50 + octave * 140 + letter / 2 * 20,
                            80, 19, 150);
                    g2.fill(temp);
                } else if (letter == 5 || letter == 7 || letter == 9 || letter == 11) {
                    Rectangle temt = new Rectangle(110 + octave * 140 + (letter - 5) / 2 * 20,
                            80, 19, 150);
                    g2.fill(temt);
                } else {
          /*do nothing*/
                }

            }

        }

        g2.setColor(Color.black);
        Line2D.Double up = new Line2D.Double(50, 80, 1450, 80);
        Line2D.Double down = new Line2D.Double(50, 230, 1450, 230);
        g2.draw(up);
        g2.draw(down);

        for (int i = 0; i < 71; i++) {
            Line2D.Double tem = new Line2D.Double(50 + 20 * i, 80, 50 + 20 * i, 230);
            g2.draw(tem);

        }

        g2.setColor(Color.black);
        for (int i = 0; i < 70; i++) {
            if (i % 7 != 2 && i % 7 != 6) {

                Rectangle tem = new Rectangle(64 + i * 20, 80, 12, 60);
                g2.fill(tem);
            }
        }

        g2.setColor(Color.YELLOW);
        for (int i = start; i <= end; i++) {
            Beat tem = mp.get(i).get(currentLine);
            if (tem != null) {
                int letter = i % 12;
                int octave = i / 12;
                if (letter == 1 || letter == 3) {
                    Rectangle temp = new Rectangle(64 + octave * 140 + (letter - 1) / 2 * 20,
                            80, 12, 60);
                    g2.fill(temp);
                } else if (letter == 6 || letter == 8 || letter == 10) {
                    Rectangle temt = new Rectangle(124 + octave * 140 + (letter - 6) / 2 * 20,
                            80, 12, 60);
                    g2.fill(temt);
                } else {
          /*do nothing*/
                }

            }

        }

        repaint();

    }
}
