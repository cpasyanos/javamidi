package cs3500.music.provider.view;

import cs3500.music.provider.model.Beat;
import cs3500.music.provider.model.MusicModel;
import cs3500.music.provider.model.MusicModel1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 28609 on 3/18/2017. this Drawing class to draw all the drawing in the GuiViewFrame.
 * it has 3 fields: HashMap --> mp, the distribution of Beats in this map.
 * int ---> numOfBeats,  the beat length of this piece of music.
 * int ---> currentLine, the current volume number its playing.
 */
class Drawing extends JPanel implements KeyListener {
    private final HashMap<Integer, HashMap<Integer, Beat>> mp;
    private final int numOfBeats;
    private int currentLine;


    /**
     * the constructor.
     *
     * @param mp         the hashmap.
     * @param numOfBeats the int.
     */
    public Drawing(HashMap<Integer, HashMap<Integer, Beat>> mp, int numOfBeats) {
        super();
        this.mp = mp;
        this.numOfBeats = numOfBeats;
        this.currentLine = 10;
        this.addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("monospaced", Font.BOLD, 20));

        MusicModel md = new MusicModel1();
        int column = (numOfBeats + 4 - numOfBeats % 4) * 20;
        List<String> loName = new ArrayList<>();
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
        int height = 300 + total * 20;
        this.setPreferredSize(new Dimension(width, height));
        for (int i = start; i <= end; i++) {
            String tem = String.format("%0$-5s", md.getPitchName(i));
            loName.add(tem);
        }

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


        g2.setColor(Color.WHITE);
        for (int i = 0; i < 70; i++) {
            Rectangle tem = new Rectangle(50 + i * 20, 80 + 20 * total, 20, 150);

            g2.draw(tem);

        }


        g2.setColor(Color.RED);
        Rectangle rec1 = new Rectangle(50 + currentLine * 20, 80, 2, 20 * total);
        g2.fill(rec1);


        g2.setColor(Color.YELLOW);
        for (int i = start; i <= end; i++) {
            Beat tem = mp.get(i).get(currentLine);
            if (tem != null) {
                int letter = i % 12;
                int octave = i / 12;
                if (letter == 0 || letter == 2 || letter == 4) {
                    Rectangle temp = new Rectangle(50 + octave * 140 + letter / 2 * 20,
                            80 + 20 * total, 19, 150);
                    g2.fill(temp);
                } else if (letter == 5 || letter == 7 || letter == 9 || letter == 11) {
                    Rectangle temt = new Rectangle(110 + octave * 140 + (letter - 5) / 2 * 20,
                            80 + 20 * total, 19, 150);
                    g2.fill(temt);
                } else {
          /*do nothing*/
                }

            }

        }

        g2.setColor(Color.black);
        for (int i = 0; i < 70; i++) {
            if (i % 7 != 2 && i % 7 != 6) {

                Rectangle tem = new Rectangle(64 + i * 20, 80 + 20 * total, 12, 60);
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
                            80 + 20 * total, 12, 60);
                    g2.fill(temp);
                } else if (letter == 6 || letter == 8 || letter == 10) {
                    Rectangle temt = new Rectangle(124 + octave * 140 + (letter - 6) / 2 * 20,
                            80 + 20 * total, 12, 60);
                    g2.fill(temt);
                } else {
          /*do nothing*/
                }

            }

        }
        repaint();


    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentLine < numOfBeats) {
                currentLine++;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentLine > 0) {
                currentLine--;
            }

        } else {
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
