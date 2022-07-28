package fractals;

import javax.swing.*;
import java.awt.*;

/** This class is to be instantiated as a slider for controlling the tree's size **/

public class SizeSlider extends MySlider {

    public SizeSlider(TreeFractal tree) {
        super(tree);
        setValue((int)(tree.getSize() / 3));
        setBackground(Color.WHITE);
        addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            double length = (((double)source.getValue()) * 3);
            tree.setSize(length);
            JWindow.pane.repaint();
        });
    }
}