package cs3500.music.provider.view;


import cs3500.music.provider.model.Beat;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {
    private final HashMap<Integer, HashMap<Integer, Beat>> mp;
    private final int numOfBeats;
    private int currentLine;


    public GuiViewFrame(HashMap<Integer, HashMap<Integer, Beat>> mp, int numOfBeats) {
        this.mp = mp;
        this.numOfBeats = numOfBeats;
        this.currentLine = 0;

    }


    @Override
    public void initialize() {
        Drawing jc = new Drawing(mp, numOfBeats);
        jc.setFocusable(true);
        jc.requestFocusInWindow();

        JScrollPane js = new JScrollPane(jc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setTitle("music view");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);

        setSize(new Dimension(1500, 1000));

        add(js, BorderLayout.CENTER);
        requestFocus();
        js.requestFocus();
        jc.requestFocus();


    }
}
