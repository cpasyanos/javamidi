package cs3500.music.provider.view;

import cs3500.music.provider.model.Beat;
import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.model.MusicModel1;

import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 28609 on 4/2/2017.
 */
class DrawingAndSound extends JPanel {
    private int currentLine;
    private Sequencer sq;
    private MusicModel mo;


    /**
     * the constructor.
     *
     * @param mo the model.
     * @param sq the sequencer.
     */
    public DrawingAndSound(MusicModel mo, Sequencer sq) {
        super();
        this.mo = mo;
        this.currentLine = 0;
        this.sq = sq;


    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("monospaced", Font.BOLD, 20));

        MusicModel md = new MusicModel1();

        HashMap<Integer, HashMap<Integer, Beat>> mp = mo.map();
        int numOfBeats = mo.number();
        int column = (numOfBeats + 4 - numOfBeats % 4) * 20;
        java.util.List<String> loName = new ArrayList<>();
        for (int d = 0; d < numOfBeats + 4; d = d + 4) {
            g2.drawString(Integer.toString(d), 47 + d * 20, 60);

        }
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
        int width = 100 + column;
        int height = total * 20;
        this.setPreferredSize(new Dimension(width, height));
        for (int i = start; i <= end; i++) {
            String tem = String.format("%0$-5s", md.getPitchName(i));
            loName.add(tem);
        }

        g2.setColor(Color.GREEN);
        for (int k = start; k <= end; k++) {
            for (int j = 0; j <= numOfBeats; j++) {
                Beat now = mp.get(k).get(j);
                if (now != null) {
                    if (now.type == 1) {
                        g2.setColor(Color.BLACK);
                        Rectangle rec = new Rectangle(51 + j * 20, 81 + (k - start) * 20,
                                20, 20);
                        g2.fill(rec);
                        g2.setColor(Color.GREEN);
                    } else {
                        Rectangle rec = new Rectangle(51 + j * 20, 81 + (k - start) * 20,
                                20, 20);
                        g2.fill(rec);

                    }
                }
            }
        }

        g2.setColor(Color.black);
        for (int k = 0; k <= end - start; k++) {
            g2.drawString(loName.get(k), 10, 100 + k * 20);
        }

        for (int k = 0; k <= end - start + 1; k++) {
            Line2D.Double tem = new Line2D.Double(50, 80 + k * 20, 50 + column,
                    80 + k * 20);
            g2.draw(tem);
        }

        for (int k = 0; k <= numOfBeats / 4 + 1; k++) {
            Line2D.Double tem = new Line2D.Double(50 + k * 80, 80, 50 + k * 80,
                    80 + 20 * total);
            g2.draw(tem);
        }

        currentLine = (int) sq.getTickPosition();

        g2.setColor(Color.RED);
        Rectangle rec1 = new Rectangle(50 + currentLine * 20, 80, 2, 20 * total);
        g2.fill(rec1);

        repaint();

    }

}
